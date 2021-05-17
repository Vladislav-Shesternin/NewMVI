package com.example.newmvi.ui.fragments.todoList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.databinding.FragmentTodoListBinding
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.ui.custom.todoRecycleView.TodoAdapter
import com.example.newmvi.ui.hideLoadingAnimation
import com.example.newmvi.ui.showLoadingAnimation
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

        return initBinding()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initComponentsUI()

        viewModel.getTodoList()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
                render(it)
            }
        }

    }

    private fun initBinding(): View {
        FragmentTodoListBinding.inflate(layoutInflater).apply {
            binding = this
            return root
        }
    }

    private fun initComponentsUI() {
        binding.apply {

            ibCreateItemTodo.setOnClickListener {
                viewModel.navigateToTodoCreatorFragment()
            }

            recycleTodoList.initAdapter(TodoAdapter {
                onTodoItemClick(it)
            })

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
                binding.lottieProgress.showLoadingAnimation()
            }
            TodoListState(
                isLoading = false,
                todoList = state.todoList
            ) -> {
                Log.i(TAG, "render: Got todoList: ${state.todoList}")
                binding.lottieProgress.hideLoadingAnimation()
                binding.recycleTodoList.setItemList(state.todoList)
            }
        }
    }

    private fun onTodoItemClick(todo: Todo) {
        viewModel.navigateToTodoEditorFragment(todo)
    }

}