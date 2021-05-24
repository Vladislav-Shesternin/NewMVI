package com.example.newmvi.domain.interactors.todoEditor

import com.example.newmvi.di.modules.qualifiers.todo.QualTodoFirebaseRealtimeRepo
import com.example.newmvi.domain.repositories.todo.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoEditorUpdateTodoInteractor @Inject constructor(
    @QualTodoFirebaseRealtimeRepo private val firebaseRepo: TodoRepo
) : BaseInteractor<TodoEditorEvent, TodoEditorState> {

    override fun invoke(
        event: Flow<TodoEditorEvent>,
        state: Flow<TodoEditorState>
    ): Flow<TodoEditorEvent> {
        return event.filterIsInstance<TodoEditorEvent.UpdateTodo>()
            .map {
                firebaseRepo.updateTodo(it.todo)
                TodoEditorEvent.UpdatedTodo
            }.flowOn(Dispatchers.IO)
    }

}