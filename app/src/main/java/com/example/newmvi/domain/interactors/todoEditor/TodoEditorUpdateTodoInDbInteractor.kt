package com.example.newmvi.domain.interactors.todoEditor

import com.example.newmvi.db.dao.TodoDao
import com.example.newmvi.db.entities.asTodoEntity
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoEditorUpdateTodoInDbInteractor @Inject constructor(
    private val todoDao: TodoDao
) : BaseInteractor<TodoEditorEvent, TodoEditorState> {

    override fun invoke(
        event: Flow<TodoEditorEvent>,
        state: Flow<TodoEditorState>
    ): Flow<TodoEditorEvent> {
        return event.filterIsInstance<TodoEditorEvent.UpdateTodoInDb>()
            .map {
                todoDao.update(it.todo.asTodoEntity())
                TodoEditorEvent.UpdatedTodoInDb
            }.flowOn(Dispatchers.IO)
    }

}