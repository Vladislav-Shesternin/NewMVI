package com.example.newmvi.navigation

sealed class NavigationCommand {

    object Back: NavigationCommand()
    data class Navigate(val screen: BaseScreen): NavigationCommand()

}
