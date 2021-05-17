package com.example.newmvi.domain.repositories

import android.graphics.Color
import android.provider.Settings
import com.example.newmvi.db.dao.TodoDao
import com.example.newmvi.db.entities.asTodoEntity
import com.example.newmvi.db.entities.asTodoEntityList
import com.example.newmvi.db.entities.asTodoList
import com.example.newmvi.domain.models.Todo
import java.util.*
import javax.inject.Inject

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepo {

    private var isFirstCall = true

    override fun getTodoList(): List<Todo> {
        return if (isFirstCall) {
            isFirstCall = false
            List(10) {
                Todo(
                    todoId = UUID.randomUUID(),
                    todoText = "Todo: ${it.inc()}",
                    todoColor = Color.CYAN
                )
            }
        } else {
            emptyList()
        }
    }

    override fun getTodoColor(color: Int): Int {
        return color
    }

    override fun insertTodoList(todoList: List<Todo>) {
        todoDao.insertList(todoList.asTodoEntityList())
    }

    override fun insertTodo(todo: Todo) {
        todoDao.insert(todo.asTodoEntity())
    }

    override fun updateTodo(todo: Todo) {
        todoDao.update(todo.asTodoEntity())
    }

    override fun getAllTodo(): List<Todo> {
        return todoDao.getAllTodo().asTodoList()
    }

}