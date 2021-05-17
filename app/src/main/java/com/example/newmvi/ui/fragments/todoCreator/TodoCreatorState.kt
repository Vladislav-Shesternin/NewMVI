package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseState

sealed class TodoCreatorState : BaseState {
    object Default : TodoCreatorState()

    object LoadColor : TodoCreatorState()
    data class LoadedColor(val color: Int) : TodoCreatorState()

    data class InsertTodo(val todo: Todo) : TodoCreatorState()
    object InsertedTodo : TodoCreatorState()
}
