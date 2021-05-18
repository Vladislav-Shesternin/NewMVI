package com.example.newmvi.db.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newmvi.domain.models.Todo
import java.util.*

@Entity(tableName = "todo_table")
data class TodoEntity(

    @PrimaryKey
    val todoId: UUID,

    @ColumnInfo(name = "text")
    var todoText: String,

    @ColumnInfo(name = "color")
    var todoColor: Int,

    )

fun Todo.asTodoEntity(): TodoEntity {
    return TodoEntity(
        todoId = todoId,
        todoText = todoText,
        todoColor = todoColor,
    )
}

fun List<Todo>.asTodoEntityList(): List<TodoEntity> {
    return map {
        TodoEntity(
            todoId = it.todoId,
            todoText = it.todoText,
            todoColor = it.todoColor,
        )
    }
}

fun List<TodoEntity>.asTodoList(): List<Todo> {
    return map {
        Todo(
            todoId = it.todoId,
            todoText = it.todoText,
            todoColor = it.todoColor,
        )
    }
}