package com.example.newmvi.ui.fragments.todoEditor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.databinding.FragmentTodoEditorBinding
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState.*
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

    private lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return initBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todo = arguments?.getParcelable("todo") ?: throw Exception("Todo not transferred")

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
                include.tvTodo.apply {
                    visibility = View.VISIBLE
                    text = todo.todoText
                    setBackgroundColor(todo.todoColor)
                }

                initLottieCheckBoxes(include)

                include.ibConfirm.setOnClickListener {
                    viewModel.updateTodoInDb(todo)
                }
            }

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
            is Default -> {
                Log.i(TAG, "render: Default")
            }
            is LoadColor -> {
                Log.i(TAG, "render: Loading ...")

                binding.includeTodoCreator.apply {
                    tvTodo.visibility = View.INVISIBLE

                    ibConfirm.visibility = View.INVISIBLE

                    lottieProgress.showLoadingAnimation()
                }
            }
            is LoadedColor -> {
                Log.i(TAG, "render: Loaded color: ${state.color}")

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
            is UpdateTodoInDb -> {
                Log.i(TAG, "render: Update todo in DB: ${state.todo}")
            }
            is UpdatedTodoInDb -> {
                viewModel.navigateBack()
            }
        }
    }
}














