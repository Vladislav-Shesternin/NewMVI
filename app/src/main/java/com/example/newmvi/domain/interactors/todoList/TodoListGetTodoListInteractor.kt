package com.example.newmvi.domain.interactors.todoList

import android.util.Log
import com.example.newmvi.domain.repos.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TodoListGetTodoListInteractor @Inject constructor(
    private val repo: TodoRepo
) : BaseInteractor<TodoListEvent, TodoListState> {

    override fun invoke(
        event: Flow<TodoListEvent>,
        state: Flow<TodoListState>
    ): Flow<TodoListEvent> {
        return event.filterIsInstance<TodoListEvent.LoadTodoList>()
            .onEach {
                repo.getTodoList()
            }
            .flatMapMerge {
                repo.getTodoListFlow()
            }
            .map {
                TodoListEvent.LoadedTodoList(it)
            }
            .flowOn(Dispatchers.IO)
    }

}

fun printVlad(it: Any) {
    Log.i("vlad", "$it")
}

fun logVlad(it: Any) {
    Log.i("vlad", "${Thread.currentThread().name} ||| $it")
}
