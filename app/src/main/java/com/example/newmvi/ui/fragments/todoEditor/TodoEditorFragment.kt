package com.example.newmvi.ui.fragments.todoEditor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newmvi.databinding.FragmentTodoCreatorBinding
import com.example.newmvi.databinding.FragmentTodoEditorBinding
import com.example.newmvi.domain.models.Todo

class TodoEditorFragment : Fragment() {

    private val TAG = this::class.simpleName

    private lateinit var binding: FragmentTodoEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val rootView = initBinding()
        initComponentsUI()
        initListeners()

        return rootView
    }

    private fun initBinding(): View {
        FragmentTodoEditorBinding.inflate(layoutInflater).apply {
            binding = this
            return root
        }
    }

    private fun initComponentsUI() {
        binding.also {
            initLottieCheckBoxes(it)
        }
    }

    private fun initLottieCheckBoxes(binding: FragmentTodoEditorBinding) {
        binding.includeTodoCreator.apply {
//            lottieCheckBoxRed.mark(viewModel.getTodoColor)
//            lottieCheckBoxGreen.mark(viewModel.getTodoColor)
//            lottieCheckBoxBlue.mark(viewModel.getTodoColor)
//            lottieCheckBoxYellow.mark(viewModel.getTodoColor)
//            lottieCheckBoxPurple.mark(viewModel.getTodoColor)
        }
    }

    private fun initListeners() {

    }

}