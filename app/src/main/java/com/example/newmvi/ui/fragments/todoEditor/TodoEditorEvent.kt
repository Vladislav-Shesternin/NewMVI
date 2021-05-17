package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent

sealed class TodoEditorEvent : BaseEvent {
    data class LoadColor(val color: Int) : TodoEditorEvent()
    data class LoadedColor(val color: Int) : TodoEditorEvent()

    data class UpdateTodoInDb(val todo: Todo) : TodoEditorEvent()
    object UpdatedTodoInDb : TodoEditorEvent()
}