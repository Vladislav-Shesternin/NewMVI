package com.example.newmvi.domain.repositories.todo

import com.example.newmvi.domain.models.Todo
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TodoRepoImpl @Inject constructor() : TodoRepo {

    override fun getTodoListFlow(): Flow<List<Todo>> {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for observeTodoFlow")
    }

    override suspend fun insertTodo(todo: Todo) {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for insertTodo")
    }

    override suspend fun insertTodoList(todoList: List<Todo>) {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for insertTodoList")
    }

    override suspend fun updateTodo(todo: Todo) {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for updateTodo")
    }

    override suspend fun getTodoList(): List<Todo> {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for getTodoList")
    }

    override suspend fun getColor(color: String) = color

}