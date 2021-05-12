package com.example.newmvi.domain.interactors.todoList

import com.example.newmvi.SubDB
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
    private val repo: TodoRepo
) : BaseInteractor<TodoListEvent, TodoListState> {

    override fun invoke(
        event: Flow<TodoListEvent>,
        state: Flow<TodoListState>
    ): Flow<TodoListEvent> {
        return event.filterIsInstance<TodoListEvent.GetTodoList>()
            .map {
                TodoListEvent.GotTodoList(getTodoList())
            }.flowOn(Dispatchers.Default)
    }

    private suspend fun getTodoList(): List<Todo> {
        return if (SubDB.isFirstOpen) {
            delay(randomTime())
            SubDB.isFirstOpen = false
            repo.getTodoList().also { SubDB.list.addAll(it) }
        } else {
            SubDB.list
        }
    }

}