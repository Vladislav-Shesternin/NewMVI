package com.example.newmvi.ui.fragments.todoList

import android.animation.ValueAnimator
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
import com.example.newmvi.databinding.FragmentTodoListBinding
import com.example.newmvi.viewModels.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TodoListFragment : Fragment(), View.OnClickListener {

    private val TAG = this::class.simpleName

    private lateinit var binding: FragmentTodoListBinding
    private val viewModel: TodoListViewModel by viewModels()

    private lateinit var ibCreateItemTodo: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = initBinding()

        initComponentUi()
        initListeners()

        viewModel.getTodoList()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                render(it)
            }
        }

        return rootView
    }

    private fun initBinding(): View {
        FragmentTodoListBinding.inflate(layoutInflater).apply {
            binding = this
            return root
        }
    }

    private fun initComponentUi() {
        binding.also {
            ibCreateItemTodo = it.ibCreateItemTodo
            it.recycleTodoList.setItemClick { tv -> onTodoItemClick(tv) }
        }
    }

    private fun initListeners() {
        ibCreateItemTodo.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            ibCreateItemTodo.id -> {
                viewModel.navigateToTodoCreatorFragment()
            }
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
                binding.recycleTodoList.setItemList(state.todoList)
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

    private fun onTodoItemClick(textView: TextView) {
        textView.text = "Hello Vlad"
    }

}