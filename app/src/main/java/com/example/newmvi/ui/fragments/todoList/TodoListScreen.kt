package com.example.newmvi.ui.fragments.todoList

import androidx.navigation.NavController
import com.example.newmvi.navigation.BaseScreen

class TodoListScreen : BaseScreen {

    override fun navigateTo(navController: NavController) {
        val action = TodoListFragmentDirections.actionTodoListToTodoCreatorFragment()
        navController.navigate(action)
    }

}