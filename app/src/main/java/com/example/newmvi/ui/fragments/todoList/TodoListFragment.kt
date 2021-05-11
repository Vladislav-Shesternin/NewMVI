package com.example.newmvi.ui.fragments.todoList

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoListBinding
import com.example.newmvi.viewModels.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TodoListFragment : Fragment() {

    private val TAG = this::class.simpleName

    private lateinit var binding: FragmentTodoListBinding

    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.getTodoList()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                render(it)
            }
        }

        return initBinding()
    }

    private fun initBinding(): View {
        FragmentTodoListBinding.inflate(layoutInflater).apply {
            binding = this
            todoList = this@TodoListFragment
            todoListViewModel = viewModel
            return root
        }
    }

    private fun render(state: TodoListState) {
        when (state) {
            TodoListState(
                isLoading = false,
                todoList = emptyList()
            ) -> {
                Log.i(TAG, "render: Default")
            }
            TodoListState(
                isLoading = true,
                todoList = emptyList()
            ) -> {
                Log.i(TAG, "render: Loading ...")
                showLoadingAnimation()
            }
            TodoListState(
                isLoading = false,
                todoList = state.todoList
            ) -> {
                Log.i(TAG, "render: Got todoList: ${state.todoList}")
                hideLoadingAnimation()
                binding.recycle.setItemList(state.todoList)
            }
        }
    }

    private fun showLoadingAnimation() {
        binding.lottieProgress.apply {
            visibility = View.VISIBLE
            repeatCount = ValueAnimator.INFINITE
            playAnimation()
        }
    }

    private fun hideLoadingAnimation() {
        binding.lottieProgress.apply {
            visibility = View.GONE
            cancelAnimation()
        }
    }

    val onItemClick: (TextView?) -> Unit = {
        Log.i(TAG, "onItemClick: ${it?.text}")
    }

}