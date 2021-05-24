package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.mvi.BaseReducer
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent.*

class TodoEditorReducer : BaseReducer<TodoEditorEvent, TodoEditorState> {

    override val initialState: TodoEditorState
        get() = TodoEditorState.Default

    override fun reduce(event: TodoEditorEvent, state: TodoEditorState): TodoEditorState {
        return when (event) {
            is LoadColor -> {
                TodoEditorState.LoadColor
            }
            is LoadedColor -> {
                TodoEditorState.LoadedColor(event.color)
            }
            is UpdateTodo -> {
                TodoEditorState.UpdateTodo(event.todo)
            }
            is UpdatedTodo -> {
                TodoEditorState.UpdatedTodo
            }
        }
    }
}