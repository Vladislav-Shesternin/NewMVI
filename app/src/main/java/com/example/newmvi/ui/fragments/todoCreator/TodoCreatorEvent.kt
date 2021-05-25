package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent

sealed class TodoCreatorEvent : BaseEvent {
    data class LoadColor(val color: String) : TodoCreatorEvent()
    data class LoadedColor(val color: String) : TodoCreatorEvent()

    data class InsertTodo(val todo: Todo) : TodoCreatorEvent()
    object InsertedTodo : TodoCreatorEvent()
    object InsertError : TodoCreatorEvent()
}
