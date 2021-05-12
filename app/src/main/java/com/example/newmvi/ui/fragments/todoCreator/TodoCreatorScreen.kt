package com.example.newmvi.ui.fragments.todoCreator

import androidx.navigation.NavController
import com.example.newmvi.R
import com.example.newmvi.navigation.BaseScreen

class TodoCreatorScreen: BaseScreen {

    override fun navigateTo(navController: NavController) {
        navController.navigate(R.id.todoCreatorFragment)
    }

}