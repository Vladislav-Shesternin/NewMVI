package com.example.newmvi.domain.interactors.todoEditor

import com.example.newmvi.domain.repos.TodoRepo
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
    private val repo: TodoRepo
) : BaseInteractor<TodoEditorEvent, TodoEditorState> {

    override fun invoke(
        event: Flow<TodoEditorEvent>,
        state: Flow<TodoEditorState>
    ): Flow<TodoEditorEvent> {
        return event.filterIsInstance<TodoEditorEvent.UpdateTodo>()
            .map {
                if (repo.updateTodo(it.todo)) {
                    TodoEditorEvent.UpdatedTodo
                } else {
                    TodoEditorEvent.UpdateError
                }
            }.flowOn(Dispatchers.IO)
    }

}