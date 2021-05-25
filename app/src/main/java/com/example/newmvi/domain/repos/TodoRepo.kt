package com.example.newmvi.domain.repos

import com.example.newmvi.domain.models.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

interface TodoRepo {

    fun getTodoListFlow(): Flow<List<Todo>>

    suspend fun insertTodo(todo: Todo): Boolean

    suspend fun insertTodoList(todoList: List<Todo>)

    suspend fun updateTodo(todo: Todo): Boolean

    suspend fun getTodoList(): List<Todo>

    suspend fun getColor(color: String): String

}