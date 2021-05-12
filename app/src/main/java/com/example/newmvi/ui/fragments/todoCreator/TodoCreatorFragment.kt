package com.example.newmvi.ui.fragments.todoCreator

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.ui.mark
import com.example.newmvi.viewModels.TodoCreatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TodoCreatorFragment : Fragment() {

    private lateinit var binding: FragmentTodoCreatorBinding

    private val viewModel: TodoCreatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = initBinding()
        initComponentsUI()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                render(it)
            }
        }

        return rootView
    }

    private fun initBinding(): View {
        FragmentTodoCreatorBinding.inflate(layoutInflater).apply {
            binding = this
            return root
        }
    }

    private fun initComponentsUI() {
        binding.also {
            initLottieCheckBoxes(it)
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

    private fun render(state: TodoCreatorState) {
        when (state) {
            TodoCreatorState(
                isLoading = false,
                color = 0
            ) -> {
                Log.i("TodoListFragment", "render: Default")
            }
            TodoCreatorState(
                isLoading = true,
                color = 0
            ) -> {
                Log.i("TodoListFragment", "render: Loading ...")
                binding.tvTodo.visibility = View.INVISIBLE
                binding.ibConfirm.visibility = View.INVISIBLE
                showLoadingColor()
            }
            TodoCreatorState(
                isLoading = false,
                color = state.color
            ) -> {
                Log.i("TodoListFragment", "render: Got color: ${state.color}")

                hideLoadingColor()
                binding.tvTodo.apply {
                    visibility = View.VISIBLE
                    setBackgroundColor(state.color)
                }
                binding.ibConfirm.visibility = View.VISIBLE
            }
        }
    }

    private fun showLoadingColor() {
        binding.lottieProgress.apply {
            visibility = View.VISIBLE
            repeatCount = ValueAnimator.INFINITE
            playAnimation()
        }
    }

    private fun hideLoadingColor() {
        binding.lottieProgress.apply {
            visibility = View.INVISIBLE
            cancelAnimation()
        }
    }

}