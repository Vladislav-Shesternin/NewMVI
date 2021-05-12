package com.example.newmvi.navigation

import androidx.fragment.app.FragmentActivity

interface BaseRouter {

    fun attach(activity: FragmentActivity)

    fun execute(command: BaseCommand)

}