package com.example.newmvi.domain.interactors.todoEditor

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

class TodoEditorInsertTodoToDBInPositionInteractor @Inject constructor(
    private val repo: TodoRepo
) : BaseInteractor<TodoEditorEvent, TodoEditorState> {

    operator fun invoke(todo: Todo, position: Int) {
        repo.insertTodoToDBInPosition(todo, position)
    }

    override fun invoke(
        event: Flow<TodoEditorEvent>,
        state: Flow<TodoEditorState>
    ): Flow<TodoEditorEvent> {
        return event.filterIsInstance<TodoEditorEvent.InsertTodoInPosition>()
            .map {
                repo.insertTodoToDBInPosition(it.todo, it.position)
                TodoEditorEvent.InsertedTodoInPosition
            }.flowOn(Dispatchers.Default) // IO
    }

}