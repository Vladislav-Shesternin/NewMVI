package com.example.newmvi.domain.repositories

import android.graphics.Color
import com.example.newmvi.domain.models.Todo
import javax.inject.Inject

class TodoRepoImpl @Inject constructor() : TodoRepo {

    override fun getTodoList(): List<Todo> {
        return List(10) { Todo("Todo: ${it.inc()}", Color.CYAN) }
    }

    override fun getTodoColor(color: Int): Int {
        return color
    }

}