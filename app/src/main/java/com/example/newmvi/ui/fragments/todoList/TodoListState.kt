package com.example.newmvi.ui.fragments.todoList

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseState

data class TodoListState(
    val isLoading: Boolean,
    val todoList: List<Todo>
) : BaseState
