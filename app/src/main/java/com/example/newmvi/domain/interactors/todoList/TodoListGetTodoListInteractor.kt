package com.example.newmvi.domain.interactors.todoList

import com.example.newmvi.db.dao.TodoDao
import com.example.newmvi.db.entities.asTodoEntityList
import com.example.newmvi.db.entities.asTodoList
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.randomTime
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoListGetTodoListInteractor @Inject constructor(
    private val repo: TodoRepo,
    private val todoDao: TodoDao,
) : BaseInteractor<TodoListEvent, TodoListState> {

    override fun invoke(
        event: Flow<TodoListEvent>,
        state: Flow<TodoListState>
    ): Flow<TodoListEvent> {
        return event.filterIsInstance<TodoListEvent.GetTodoList>()
            .map {
                TodoListEvent.GotTodoList(getTodoList())
            }.flowOn(Dispatchers.IO)
    }

    private suspend fun getTodoList(): List<Todo> {
        repo.getTodoList().also {
            if (it.isNotEmpty()) {
                delay(randomTime())
                todoDao.insert(it.asTodoEntityList())
            }
        }
        return todoDao.getAllTodo().asTodoList()
    }

}