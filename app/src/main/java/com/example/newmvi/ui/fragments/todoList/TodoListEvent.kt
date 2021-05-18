package com.example.newmvi.ui.fragments.todoList

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseEvent
import kotlinx.coroutines.flow.Flow

sealed class TodoListEvent : BaseEvent {
    object LoadTodoList : TodoListEvent()
    data class LoadedTodoList(val todoList: Flow<List<Todo>>) : TodoListEvent()
}
