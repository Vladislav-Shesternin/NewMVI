package com.example.newmvi.ui.fragments.todoEditor

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.databinding.FragmentTodoEditorBinding
import com.example.newmvi.domain.interactors.todoList.printVlad
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
                include.viewTodo.apply {
                    visibility = View.VISIBLE
                    setBackgroundColor(Color.parseColor(todo.todoColor))
                }

                include.editTodo.apply {
                    visibility = View.VISIBLE
                    setText(todo.todoText)
                }

                initLottieCheckBoxes(include)

                include.ibConfirm.setOnClickListener {
                    todo.todoText = include.editTodo.text.toString()
                    viewModel.updateTodo(todo)
                }
            }
        }

    }

    private fun initLottieCheckBoxes(binding: FragmentTodoCreatorBinding) {
        binding.apply {
            lottieCheckBoxRed.mark(viewModel.getColor)
            lottieCheckBoxGreen.mark(viewModel.getColor)
            lottieCheckBoxBlue.mark(viewModel.getColor)
            lottieCheckBoxYellow.mark(viewModel.getColor)
            lottieCheckBoxPurple.mark(viewModel.getColor)
        }
    }

    private fun render(state: TodoEditorState) {
        when (state) {
            is Default -> {
                printVlad("render: Default")
            }
            is LoadColor -> {
                printVlad("render: Loading ...")

                binding.includeTodoCreator.apply {
                    viewTodo.visibility = View.INVISIBLE

                    editTodo.visibility = View.INVISIBLE

                    ibConfirm.visibility = View.INVISIBLE

                    lottieProgress.showLoadingAnimation()
                }
            }
            is LoadedColor -> {
                printVlad("render: Loaded color: ${state.color}")

                todo.todoColor = state.color

                binding.includeTodoCreator.apply {
                    lottieProgress.hideLoadingAnimation()

                    viewTodo.apply {
                        visibility = View.VISIBLE
                        setBackgroundColor(Color.parseColor(state.color))
                    }

                    editTodo.visibility = View.VISIBLE

                    ibConfirm.visibility = View.VISIBLE
                }
            }
            is UpdateTodo -> {
                printVlad("render: Update todo: ${state.todo}")
            }
            is UpdatedTodo -> {
                viewModel.navigateBack()
            }
            is UpdateError -> {
                Toast.makeText(this.requireContext(), "Update error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}














