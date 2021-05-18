package com.example.newmvi.domain.interactors.todoList

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.randomTime
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TodoListGetTodoListInteractor @Inject constructor(
    private val todoRepo: TodoRepo,
) : BaseInteractor<TodoListEvent, TodoListState> {

    override fun invoke(
        event: Flow<TodoListEvent>,
        state: Flow<TodoListState>
    ): Flow<TodoListEvent> {
        return event.filterIsInstance<TodoListEvent.LoadTodoList>()
            .map {
                TodoListEvent.LoadedTodoList(loadTodoList())
            }.flowOn(Dispatchers.IO)
    }

    private fun loadTodoList(): Flow<List<Todo>> {
        todoRepo.loadTodoList()
        return todoRepo.getAllTodo()
    }

}