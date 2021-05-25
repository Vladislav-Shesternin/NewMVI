package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.mvi.BaseReducer
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent.*

class TodoCreatorReducer : BaseReducer<TodoCreatorEvent, TodoCreatorState> {

    override val initialState: TodoCreatorState
        get() = TodoCreatorState.Default

    override fun reduce(event: TodoCreatorEvent, state: TodoCreatorState): TodoCreatorState {
        return when (event) {
            is LoadColor -> {
                TodoCreatorState.LoadColor
            }
            is LoadedColor -> {
                TodoCreatorState.LoadedColor(event.color)
            }
            is InsertTodo -> {
                TodoCreatorState.InsertTodo(event.todo)
            }
            is InsertedTodo -> {
                TodoCreatorState.InsertedTodo
            }
            is InsertError -> {
                TodoCreatorState.InsertError
            }
        }
    }

}