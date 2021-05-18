package com.example.newmvi.domain.repositories

import com.example.newmvi.domain.models.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepo {

    fun loadTodoList()

    fun getTodoColor(color: Int): Int

    fun insertTodo(todo: Todo)

    fun updateTodo(todo: Todo)

    fun getAllTodo(): Flow<List<Todo>>
}