package com.example.newmvi.db.firebaseRealtime.models

import com.example.newmvi.domain.models.Todo
import java.util.*

data class TodoModel(
    val text: String = "",
    val color: String = "",
)

fun TodoModel.asTodo(id: UUID): Todo {
    return Todo(
        todoId = id,
        todoText = text,
        todoColor = color
    )
}

fun Todo.asTodoModel(): TodoModel {
    return TodoModel(
        text = todoText,
        color = todoColor.toString()
    )
}