package com.example.newmvi.db.firebaseRealtime.models

import android.graphics.Color
import com.example.newmvi.db.room.entities.TodoEntity
import com.example.newmvi.domain.models.Todo
import java.util.*

data class TodoModel(
    var id: String = "",
    val text: String = "",
    val color: String = "",
)

fun TodoModel.asTodo(): Todo {
    return Todo(
        todoId = UUID.fromString(id),
        todoText = text,
        todoColor = Color.parseColor(color)
    )
}