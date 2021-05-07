package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.mvi.BaseReducer

class TodoCreatorReducer : BaseReducer<TodoCreatorEvent, TodoCreatorState> {

    override val initialState: TodoCreatorState
        get() = TodoCreatorState(
            isLoading = false,
            color = 0
        )

    override fun reduce(event: TodoCreatorEvent, state: TodoCreatorState): TodoCreatorState {
        return when (event) {
            is TodoCreatorEvent.GetColor -> {
                state.copy(
                    isLoading = true,
                    color = 0
                )
            }
            is TodoCreatorEvent.GotColor -> {
                state.copy(
                    isLoading = false,
                    color = event.color
                )
            }
        }
    }

}