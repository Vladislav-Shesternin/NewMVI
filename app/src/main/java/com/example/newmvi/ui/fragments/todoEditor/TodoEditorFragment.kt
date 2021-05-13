package com.example.newmvi.ui.fragments.todoEditor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.SubDB
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.databinding.FragmentTodoEditorBinding
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.ui.hideLoadingAnimation
import com.example.newmvi.ui.mark
import com.example.newmvi.ui.showLoadingAnimation
import com.example.newmvi.viewModels.TodoEditorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TodoEditorFragment : Fragment() {

    private val TAG = this::class.simpleName

    private lateinit var binding: FragmentTodoEditorBinding
    private val viewModel: TodoEditorViewModel by viewModels()

    private val todo = Todo("", 0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return initBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponentsUI()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                render(it)
            }
        }

    }

    private fun initBinding(): View {
        FragmentTodoEditorBinding.inflate(layoutInflater).apply {
            binding = this
            return root
        }
    }

    private fun initComponentsUI() {
        binding.apply {

            this.includeTodoCreator.also { include ->

                initTodo(
                    textView = include.tvTodo,
                    todo = arguments?.getParcelable<Todo>("todo") ?: Todo("TODO", 0)
                )

                initLottieCheckBoxes(include)

                include.ibConfirm.setOnClickListener {
                    val position =
                        arguments?.getInt("position") ?: viewModel.getAllTodoFromDb().size - 1
                    viewModel.addTodoToDBInPosition(todo, position)
                    viewModel.navigateBack()
                }

            }

        }
    }

    private fun initTodo(textView: TextView, todo: Todo) {
        this.todo.todoText = todo.todoText

        textView.apply {
            visibility = View.VISIBLE
            text = todo.todoText
            setBackgroundColor(todo.todoColor)
        }
    }

    private fun initLottieCheckBoxes(binding: FragmentTodoCreatorBinding) {
        binding.apply {
            lottieCheckBoxRed.mark(viewModel.getTodoColor)
            lottieCheckBoxGreen.mark(viewModel.getTodoColor)
            lottieCheckBoxBlue.mark(viewModel.getTodoColor)
            lottieCheckBoxYellow.mark(viewModel.getTodoColor)
            lottieCheckBoxPurple.mark(viewModel.getTodoColor)
        }
    }

    private fun render(state: TodoEditorState) {
        when (state) {
            TodoEditorState(
                isLoading = false,
                color = 0
            ) -> {
                Log.i(TAG, "render: Default")
            }
            TodoEditorState(
                isLoading = true,
                color = 0
            ) -> {
                Log.i(TAG, "render: Loading ...")

                binding.includeTodoCreator.apply {
                    tvTodo.visibility = View.INVISIBLE

                    ibConfirm.visibility = View.INVISIBLE

                    lottieProgress.showLoadingAnimation()
                }
            }
            TodoEditorState(
                isLoading = false,
                color = state.color
            ) -> {
                Log.i(TAG, "render: Got color: ${state.color}")

                todo.todoColor = state.color

                binding.includeTodoCreator.apply {
                    lottieProgress.hideLoadingAnimation()

                    tvTodo.apply {
                        visibility = View.VISIBLE
                        setBackgroundColor(state.color)
                    }

                    ibConfirm.visibility = View.VISIBLE
                }
            }
        }
    }
}














