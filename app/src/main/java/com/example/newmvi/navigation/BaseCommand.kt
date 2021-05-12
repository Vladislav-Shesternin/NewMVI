package com.example.newmvi.navigation

sealed class BaseCommand {

    object Back: BaseCommand()
    data class Navigate(val screen: BaseScreen): BaseCommand()

}
