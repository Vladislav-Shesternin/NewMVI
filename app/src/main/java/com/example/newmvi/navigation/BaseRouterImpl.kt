package com.example.newmvi.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.newmvi.R
import javax.inject.Inject

class BaseRouterImpl @Inject constructor() : BaseRouter {

    private var navController: NavController? = null

    override fun attach(activity: FragmentActivity) {
        navController = activity.findNavController(R.id.nav_host_fragment)
    }

    override fun execute(command: BaseCommand) {
        navController?.let { controller ->
            when (command) {
                is BaseCommand.Back -> {
                    controller.popBackStack()
                }
                is BaseCommand.Navigate -> {
                    command.screen.navigateTo(controller)
                }
            }
        }
    }

}