package com.example.newmvi.ui.fragments.todoCreator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.todoAmount
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

    private val todo = Todo(UUID.randomUUID(), "TODO: $todoAmount", 0)

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
                viewModel.insertTodoInDb(todo)
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
                Log.i("TodoListFragment", "render: Default")
            }
            is LoadColor -> {
                Log.i("TodoListFragment", "render: Loading ...")

                binding.apply {
                    tvTodo.visibility = View.INVISIBLE

                    ibConfirm.visibility = View.INVISIBLE

                    lottieProgress.showLoadingAnimation()
                }
            }
            is LoadedColor -> {
                Log.i("TodoListFragment", "render: Got color: ${state.color}")

                todo.todoColor = state.color

                binding.apply {
                    lottieProgress.hideLoadingAnimation()

                    tvTodo.apply {
                        visibility = View.VISIBLE
                        setBackgroundColor(state.color)
                    }

                    ibConfirm.visibility = View.VISIBLE
                }
            }
            is InsertTodo -> {
                Log.i(TAG, "render: Insert todo: ${state.todo}")
            }
            is InsertedTodo -> {
                Log.i(TAG, "render: Inserted todo")

                viewModel.navigateBack()
            }
        }
    }
}