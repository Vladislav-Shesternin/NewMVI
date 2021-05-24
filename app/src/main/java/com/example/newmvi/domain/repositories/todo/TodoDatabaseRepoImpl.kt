package com.example.newmvi.domain.repositories.todo

import com.example.newmvi.db.room.dao.TodoDao
import com.example.newmvi.db.room.entities.asTodoEntity
import com.example.newmvi.db.room.entities.asTodoEntityList
import com.example.newmvi.db.room.entities.asTodoList
import com.example.newmvi.domain.models.Todo
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TodoDatabaseRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
) : TodoRepo {

    override fun getTodoListFlow(): Flow<List<Todo>> {
        return todoDao.getFlowTodo().map { it.asTodoList() }
    }

    override suspend fun insertTodo(todo: Todo) {
        todoDao.insert(todo.asTodoEntity())
    }

    override suspend fun insertTodoList(todoList: List<Todo>) {
        todoDao.insertList(todoList.asTodoEntityList())
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDao.update(todo.asTodoEntity())
    }

    override suspend fun getTodoList(): List<Todo> {
        return todoDao.getAllTodo().asTodoList()
    }

    override suspend fun getColor(color: String): String {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for getColor")
    }

}