package com.example.newmvi.viewModels

import android.util.Log
import com.example.newmvi.domain.interactors.GetTodoListInteractor
import com.example.newmvi.mvi.BaseViewModel
import com.example.newmvi.navigation.BaseCommand
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListReducer
import com.example.newmvi.ui.fragments.todoList.TodoListScreen
import com.example.newmvi.ui.fragments.todoList.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    getTodoList: GetTodoListInteractor,
    private val router: BaseRouter,
) : BaseViewModel<TodoListEvent, TodoListState>(
    interactors = setOf(getTodoList),
    reducer = TodoListReducer()
) {

    fun getTodoList() {
        setEvent(TodoListEvent.GetTodoList)
    }

    fun navigateToTodoCreatorFragment() {
        Log.i("TodoListFragment", "navigateToTodoCreatorFragment: ")
        router.execute(BaseCommand.Navigate(TodoListScreen()))
    }

}