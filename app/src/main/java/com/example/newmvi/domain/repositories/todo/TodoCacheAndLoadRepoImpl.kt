package com.example.newmvi.domain.repositories.todo

import com.example.newmvi.di.modules.qualifiers.todo.QualTodoDatabaseRepo
import com.example.newmvi.di.modules.qualifiers.todo.QualTodoFirebaseRealtimeRepo
import com.example.newmvi.domain.interactors.todoList.logVlad
import com.example.newmvi.domain.interactors.todoList.printVlad
import com.example.newmvi.domain.models.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoCacheAndLoadRepoImpl @Inject constructor(
    @QualTodoDatabaseRepo private val databaseRepo: TodoRepo,
    @QualTodoFirebaseRealtimeRepo private val firebaseRealtimeRepo: TodoRepo,
) : TodoRepo {

    override fun getTodoListFlow(): Flow<List<Todo>> {
        return databaseRepo.getTodoListFlow()
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
        cacheTodoList()
        return emptyList()
    }

    private suspend fun cacheTodoList() {
        val todoList = firebaseRealtimeRepo.getTodoList()
        databaseRepo.insertTodoList(todoList)
    }

    override suspend fun getColor(color: String): String {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for getColor")
    }

}