package com.example.newmvi.domain.interactors.todoCreator

import com.example.newmvi.domain.repos.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.randomTime
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoCreatorLoadTodoColorInteractor @Inject constructor(
    private val repo: TodoRepo,
) : BaseInteractor<TodoCreatorEvent, TodoCreatorState> {

    override fun invoke(
        event: Flow<TodoCreatorEvent>,
        state: Flow<TodoCreatorState>
    ): Flow<TodoCreatorEvent> {
        return event.filterIsInstance<TodoCreatorEvent.LoadColor>()
            .map {
                delay(randomTime())
                TodoCreatorEvent.LoadedColor(repo.getColor(it.color))
            }.flowOn(Dispatchers.Default)
    }

}