package com.example.newmvi.domain.interactors

import android.util.Log
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

class GetTodoListInteractor @Inject constructor(
    private val repo: TodoRepo
) : BaseInteractor<TodoListEvent, TodoListState> {

    override fun invoke(
        event: Flow<TodoListEvent>,
        state: Flow<TodoListState>
    ): Flow<TodoListEvent> {
        return event.filterIsInstance<TodoListEvent.GetTodoList>()
            .map {
                val time = randomTime()
                Log.i("TodoListFragment", "delay: $time")
                delay(time)
                TodoListEvent.GotTodoList(repo.getTodoList())
            }.flowOn(Dispatchers.Default)
    }

}