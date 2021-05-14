package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent

sealed class TodoEditorEvent : BaseEvent {
    data class GetColor(val color: Int) : TodoEditorEvent()
    data class GotColor(val color: Int) : TodoEditorEvent()

    object GetAllTodo : TodoEditorEvent()
    data class GotAllTodo(val todoList: List<Todo>) : TodoEditorEvent()

    data class InsertTodoInPosition(val todo: Todo, val position: Int) : TodoEditorEvent()
    object InsertedTodoInPosition : TodoEditorEvent()
}