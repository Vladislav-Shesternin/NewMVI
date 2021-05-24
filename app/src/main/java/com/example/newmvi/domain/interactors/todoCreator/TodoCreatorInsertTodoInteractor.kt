package com.example.newmvi.domain.interactors.todoCreator

import com.example.newmvi.di.modules.qualifiers.todo.QualTodoFirebaseRealtimeRepo
import com.example.newmvi.domain.repositories.todo.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoCreatorInsertTodoInteractor @Inject constructor(
    @QualTodoFirebaseRealtimeRepo private val firebaseRepo: TodoRepo
) : BaseInteractor<TodoCreatorEvent, TodoCreatorState> {

    override fun invoke(
        event: Flow<TodoCreatorEvent>,
        state: Flow<TodoCreatorState>
    ): Flow<TodoCreatorEvent> {
        return event.filterIsInstance<TodoCreatorEvent.InsertTodo>()
            .map {
                firebaseRepo.insertTodo(it.todo)
                TodoCreatorEvent.InsertedTodo
            }.flowOn(Dispatchers.IO)
    }

}