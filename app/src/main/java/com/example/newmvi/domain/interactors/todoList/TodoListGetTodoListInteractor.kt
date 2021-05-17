package com.example.newmvi.domain.interactors.todoList

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.randomTime
import com.example.newmvi.todoAmount
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
    private val todoRepo: TodoRepo,
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
        todoRepo.getTodoList().also {
            if (it.isNotEmpty()) {
                delay(randomTime())
                todoAmount = it.size.inc()
                todoRepo.insertTodoList(it)
            }
        }
        return todoRepo.getAllTodo()
    }

}