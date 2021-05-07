package com.example.newmvi.ui.fragments.todoList

import com.example.newmvi.mvi.BaseReducer

class TodoListReducer: BaseReducer<TodoListEvent, TodoListState> {

    override val initialState: TodoListState
        get() = TodoListState(
            isLoading = false,
            todoList = emptyList()
        )

    override fun reduce(event: TodoListEvent, state: TodoListState): TodoListState {
        return when(event){
            is TodoListEvent.GetTodoList -> {
                state.copy(
                    isLoading = true,
                    todoList = emptyList()
                )
            }
            is TodoListEvent.GotTodoList -> {
                state.copy(
                    isLoading = false,
                    todoList = event.todoList
                )
            }
        }
    }

}