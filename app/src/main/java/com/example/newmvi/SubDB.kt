package com.example.newmvi

import com.example.newmvi.domain.models.Todo

object SubDB {

    var isFirstOpen = true

    val list = mutableListOf<Todo>()

}