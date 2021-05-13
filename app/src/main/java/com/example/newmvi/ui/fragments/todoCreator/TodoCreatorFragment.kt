package com.example.newmvi.ui.fragments.todoCreator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newmvi.SubDB
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.ui.hideLoadingAnimation
import com.example.newmvi.ui.mark
import com.example.newmvi.ui.showLoadingAnimation
import com.example.newmvi.viewModels.TodoCreatorViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TodoCreatorFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentTodoCreatorBinding
    private val viewModel: TodoCreatorViewModel by viewModels()

    private lateinit var ibConfirm: ImageButton

    private var todo = Todo("", 0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = initBinding()
        initComponentsUI()
        initListeners()

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
            ibConfirm = it.ibConfirm
            initLottieCheckBoxes(it)
        }
    }

    private fun initListeners() {
        ibConfirm.setOnClickListener(this)
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

                binding.apply {
                    tvTodo.visibility = View.INVISIBLE

                    ibConfirm.visibility = View.INVISIBLE

                    lottieProgress.showLoadingAnimation()
                }
            }
            TodoCreatorState(
                isLoading = false,
                color = state.color
            ) -> {
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
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            ibConfirm.id -> {
                todo.todoText = "TODO ${SubDB.list.size.inc()}"
                SubDB.list.add(todo)
                viewModel.navigateBack()
            }
        }
    }

}