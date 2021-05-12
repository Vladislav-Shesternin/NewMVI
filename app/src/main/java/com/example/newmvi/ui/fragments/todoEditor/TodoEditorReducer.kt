package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.mvi.BaseReducer

class TodoEditorReducer : BaseReducer<TodoEditorEvent, TodoEditorState> {

    override val initialState: TodoEditorState
        get() = TodoEditorState(
            isLoading = false,
            color = 0
        )

    override fun reduce(event: TodoEditorEvent, state: TodoEditorState): TodoEditorState {
        return when (event) {
            is TodoEditorEvent.GetColor -> {
                state.copy(
                    isLoading = true,
                    color = 0
                )
            }
            is TodoEditorEvent.GotColor -> {
                state.copy(
                    isLoading = false,
                    color = event.color
                )
            }
        }
    }
}