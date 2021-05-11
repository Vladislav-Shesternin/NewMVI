package com.example.newmvi.navigation

sealed class BaseCommand {

    data class Navigate(val screen: BaseScreen): BaseCommand()

}
