package com.example.newmvi.domain.interactors.todoList

import android.util.Log
import com.example.newmvi.di.modules.qualifiers.todo.QualTodoCacheAndLoadRepo
import com.example.newmvi.domain.repositories.todo.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TodoListGetTodoListInteractor @Inject constructor(
    @QualTodoCacheAndLoadRepo val todoRepo: TodoRepo
) : BaseInteractor<TodoListEvent, TodoListState> {

    override fun invoke(
        event: Flow<TodoListEvent>,
        state: Flow<TodoListState>
    ): Flow<TodoListEvent> {
        return event.filterIsInstance<TodoListEvent.LoadTodoList>()
            .onEach {
                logVlad("Loading...")
                todoRepo.getTodoList()
            }
            .flatMapMerge {
                logVlad("Update")
                todoRepo.getTodoListFlow()
            }
            .map {
                logVlad("map = $it")
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
