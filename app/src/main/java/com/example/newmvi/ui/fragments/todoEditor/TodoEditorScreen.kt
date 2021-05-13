package com.example.newmvi.ui.fragments.todoEditor

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.newmvi.R
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.navigation.BaseScreen

class TodoEditorScreen(
    private val todo: Todo,
    private val position: Int,
) : BaseScreen {

    override fun navigateTo(navController: NavController) {
        navController.navigate(R.id.todoEditorFragment, bundleOf(
            "todo" to todo,
            "position" to position,
        ))
    }

}