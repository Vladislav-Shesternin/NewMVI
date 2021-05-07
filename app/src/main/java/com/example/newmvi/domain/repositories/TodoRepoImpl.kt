package com.example.newmvi.domain.repositories

import com.example.newmvi.domain.models.Todo
import javax.inject.Inject

class TodoRepoImpl @Inject constructor() : TodoRepo {

    override fun getTodoList(): List<Todo> {
        return List(50) { Todo("Todo: ${it.inc()}") }
    }

    override fun getTodoColor(color: Int): Int {
        return color
    }

}