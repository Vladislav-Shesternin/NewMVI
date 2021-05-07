package com.example.newmvi.ui.fragments.todoList

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent

sealed class TodoListEvent : BaseEvent {
    object GetTodoList : TodoListEvent()
    data class GotTodoList(val todoList: List<Todo>) : TodoListEvent()
}
