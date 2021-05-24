package com.example.newmvi.ui.fragments.todoCreator

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.domain.interactors.todoList.printVlad
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState.*
import com.example.newmvi.ui.hideLoadingAnimation
import com.example.newmvi.ui.mark
import com.example.newmvi.ui.showLoadingAnimation
import com.example.newmvi.viewModels.TodoCreatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class TodoCreatorFragment : Fragment() {

    private val TAG = this::class.simpleName

    private lateinit var binding: FragmentTodoCreatorBinding
    private val viewModel: TodoCreatorViewModel by viewModels()

    private val todo = Todo(UUID.randomUUID(), "", "")

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
        FragmentTodoCreatorBinding.inflate(layoutInflater).apply {
            binding = this
            return root
        }
    }

    private fun initComponentsUI() {
        binding.apply {

            ibConfirm.setOnClickListener {
                todo.todoText = editTodo.text.toString()
                viewModel.insertTodo(todo)
            }

            initLottieCheckBoxes(this)
        }
    }

    private fun initLottieCheckBoxes(binding: FragmentTodoCreatorBinding) {
        with(binding) {
            lottieCheckBoxRed.mark(viewModel.loadColor)
            lottieCheckBoxGreen.mark(viewModel.loadColor)
            lottieCheckBoxBlue.mark(viewModel.loadColor)
            lottieCheckBoxYellow.mark(viewModel.loadColor)
            lottieCheckBoxPurple.mark(viewModel.loadColor)
        }
    }

    private fun render(state: TodoCreatorState) {
        when (state) {
            is Default -> {

            }
            is LoadColor -> {

                binding.apply {
                    viewTodo.visibility = View.INVISIBLE

                    editTodo.visibility = View.INVISIBLE

                    ibConfirm.visibility = View.INVISIBLE

                    lottieProgress.showLoadingAnimation()
                }
            }
            is LoadedColor -> {
                todo.todoColor = state.color

                binding.apply {
                    lottieProgress.hideLoadingAnimation()

                    viewTodo.apply {
                        visibility = View.VISIBLE
                        setBackgroundColor(Color.parseColor(state.color))
                    }

                    editTodo.visibility = View.VISIBLE

                    ibConfirm.visibility = View.VISIBLE
                }
            }
            is InsertTodo -> {

            }
            is InsertedTodo -> {
                viewModel.navigateBack()
            }
        }
    }
}