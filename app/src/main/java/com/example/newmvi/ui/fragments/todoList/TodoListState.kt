package com.example.newmvi.ui.fragments.todoList

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseState
import kotlinx.coroutines.flow.Flow

sealed class TodoListState : BaseState {
    object Default : TodoListState()

    object LoadTodoList : TodoListState()
    data class LoadedTodoList(val todoList: List<Todo>) : TodoListState()
}

