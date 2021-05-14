package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent

sealed class TodoCreatorEvent : BaseEvent {
    data class GetColor(val color: Int) : TodoCreatorEvent()
    data class GotColor(val color: Int) : TodoCreatorEvent()

    object GetAllTodo : TodoCreatorEvent()
    data class GotAllTodo(val todoList: List<Todo>) : TodoCreatorEvent()

    data class InsertTodo(val todo: Todo) : TodoCreatorEvent()
    object InsertedTodo : TodoCreatorEvent()
}
