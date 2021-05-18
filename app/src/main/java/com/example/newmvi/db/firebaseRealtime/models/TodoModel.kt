package com.example.newmvi.db.firebaseRealtime.models

import android.graphics.Color
import com.example.newmvi.db.room.entities.TodoEntity
import java.util.*

data class TodoModel(
    var id: String = "",
    val text: String = "",
    val color: String = "",
)

fun TodoModel.asTodoEntity(): TodoEntity {
    return TodoEntity(
        todoId = UUID.fromString(id),
        todoText = text,
        todoColor = Color.parseColor(color)
    )
}