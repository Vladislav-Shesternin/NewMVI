package com.example.newmvi.data

import com.example.newmvi.db.todo.QualTodoDatabaseRepo
import com.example.newmvi.db.todo.QualTodoFirebaseRealtimeRepo
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repos.TodoRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoCacheAndLoadRepoImpl @Inject constructor(
    @QualTodoDatabaseRepo private val databaseRepo: TodoRepo,
    @QualTodoFirebaseRealtimeRepo private val firebaseRealtimeRepo: TodoRepo,
) : TodoRepo {

    override fun getTodoListFlow(): Flow<List<Todo>> {
        return databaseRepo.getTodoListFlow()
    }

    override suspend fun insertTodo(todo: Todo): Boolean {
        return firebaseRealtimeRepo.insertTodo(todo)
    }

    override suspend fun insertTodoList(todoList: List<Todo>) {
        databaseRepo.insertTodoList(todoList)
    }

    override suspend fun updateTodo(todo: Todo): Boolean {
        return firebaseRealtimeRepo.updateTodo(todo)
    }

    override suspend fun getTodoList(): List<Todo> {
        cacheTodoList()
        return emptyList()
    }

    private suspend fun cacheTodoList() {
        val todoList = firebaseRealtimeRepo.getTodoList()
        databaseRepo.insertTodoList(todoList)
    }

    override suspend fun getColor(color: String) = color

}