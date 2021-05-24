package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent

sealed class TodoEditorEvent : BaseEvent {
    data class LoadColor(val color: String) : TodoEditorEvent()
    data class LoadedColor(val color: String) : TodoEditorEvent()

    data class UpdateTodo(val todo: Todo) : TodoEditorEvent()
    object UpdatedTodo : TodoEditorEvent()
}