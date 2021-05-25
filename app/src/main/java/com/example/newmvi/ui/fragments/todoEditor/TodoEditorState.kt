package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseState

sealed class TodoEditorState : BaseState {
    object Default : TodoEditorState()

    object LoadColor : TodoEditorState()
    data class LoadedColor(val color: String) : TodoEditorState()

    data class UpdateTodo(val todo: Todo) : TodoEditorState()
    object UpdatedTodo : TodoEditorState()
    object UpdateError : TodoEditorState()
}