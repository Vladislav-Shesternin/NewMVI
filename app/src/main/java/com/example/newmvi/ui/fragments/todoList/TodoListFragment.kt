package com.example.newmvi.ui.fragments.todoList

import android.graphics.drawable.ColorDrawable
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
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.ui.hideLoadingAnimation
import com.example.newmvi.ui.showLoadingAnimation
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

        initComponentsUI()
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

    private fun initComponentsUI() {
        binding.also {
            ibCreateItemTodo = it.ibCreateItemTodo
            it.recycleTodoList.setItemClick { textView, position ->
                onTodoItemClick(
                    textView,
                    position
                )
            }
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

    private fun onTodoItemClick(textView: TextView, position: Int) {

        val text = textView.text.toString()
        val color = (textView.background as ColorDrawable).color

        viewModel.navigateToTodoEditorFragment(
            todo = Todo(text, color),
            position = position
        )
    }

}