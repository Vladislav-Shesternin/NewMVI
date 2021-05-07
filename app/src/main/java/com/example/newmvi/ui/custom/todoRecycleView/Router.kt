package com.example.newmvi.ui.custom.todoRecycleView

import androidx.navigation.NavController

interface Router {
    fun execute(command: Command)
}

interface Screen {

    fun navigate(navController: NavController)

}

sealed class Command {
    object Back : Command()
    data class Navigate(val screen: Screen) : Command()
}

class RouterImpl(private val navController: NavController) : Router {

    override fun execute(command: Command) {
        when(command) {
            Command.Back -> navController.popBackStack()
            is Command.Navigate -> {
                command.screen.navigate(navController)
            }
        }
    }

}