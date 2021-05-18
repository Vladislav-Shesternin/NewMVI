package com.example.newmvi.ui.fragments.todoList

import com.example.newmvi.mvi.BaseReducer
import com.example.newmvi.ui.fragments.todoList.TodoListEvent.LoadTodoList
import com.example.newmvi.ui.fragments.todoList.TodoListEvent.LoadedTodoList

class TodoListReducer: BaseReducer<TodoListEvent, TodoListState> {

    override val initialState: TodoListState
        get() = TodoListState.Default

    override fun reduce(event: TodoListEvent, state: TodoListState): TodoListState {
        return when(event){
            is LoadTodoList -> {
                TodoListState.LoadTodoList
            }
            is LoadedTodoList -> {
                TodoListState.LoadedTodoList(event.todoList)
            }
        }
    }

}