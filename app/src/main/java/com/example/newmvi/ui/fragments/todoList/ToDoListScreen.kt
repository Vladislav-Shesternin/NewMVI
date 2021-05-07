package com.example.newmvi.ui.fragments.todoList

import androidx.navigation.NavController
import com.example.newmvi.R
import com.example.newmvi.ui.custom.todoRecycleView.Screen

class ToDoListScreen(val arg: Int) : Screen{
    override fun navigate(navController: NavController) {
        navController.navigate(R.id., arg)
    }
}