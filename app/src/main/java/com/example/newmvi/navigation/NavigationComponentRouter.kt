package com.example.newmvi.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.newmvi.R
import javax.inject.Inject

class NavigationComponentRouter @Inject constructor() : BaseRouter {

    private var navController: NavController? = null

    override fun attach(activity: FragmentActivity) {
        navController = activity.findNavController(R.id.nav_host_fragment)
    }

    override fun execute(command: NavigationCommand) {
        navController?.let { controller ->
            when (command) {
                is NavigationCommand.Back -> {
                    controller.popBackStack()
                }
                is NavigationCommand.Navigate -> {
                    command.screen.navigateTo(controller)
                }
            }
        }
    }

}