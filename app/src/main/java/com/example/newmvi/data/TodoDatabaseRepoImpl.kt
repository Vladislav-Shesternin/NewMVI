package com.example.newmvi.data

import com.example.newmvi.db.room.dao.TodoDao
import com.example.newmvi.db.room.entities.asTodoEntity
import com.example.newmvi.db.room.entities.asTodoEntityList
import com.example.newmvi.db.room.entities.asTodoList
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repos.TodoRepo
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

class TodoDatabaseRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
) : TodoRepo {

    override fun getTodoListFlow(): Flow<List<Todo>> {
        return todoDao.getFlowTodo().map { it.asTodoList() }
    }

    override suspend fun insertTodo(todo: Todo): Boolean {
        return try {
            todoDao.insert(todo.asTodoEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun insertTodoList(todoList: List<Todo>) {
        todoDao.insertList(todoList.asTodoEntityList())
    }

    override suspend fun updateTodo(todo: Todo): Boolean {
        return try {
            todoDao.update(todo.asTodoEntity())
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getTodoList(): List<Todo> {
        return todoDao.getAllTodo().asTodoList()
    }

    override suspend fun getColor(color: String) = color

}