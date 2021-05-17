package com.example.newmvi.domain.repositories

import android.graphics.Color
import android.provider.Settings
import com.example.newmvi.domain.models.Todo
import java.util.*
import javax.inject.Inject

class TodoRepoImpl @Inject constructor() : TodoRepo {

    private var isFirstCall = true

    override fun getTodoList(): List<Todo> {
        return if (isFirstCall) {
            isFirstCall = false
            List(10) {
                Todo(
                    todoId = UUID.randomUUID(),
                    todoText = "Todo: ${it.inc()}",
                    todoColor = Color.CYAN
                )
            }
        } else {
            emptyList()
        }
    }

    override fun getTodoColor(color: Int): Int {
        return color
    }

}