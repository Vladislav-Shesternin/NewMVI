package com.example.newmvi.viewModels

import com.example.newmvi.base.BaseViewModel
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.navigation.NavigationCommand
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorScreen
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorScreen
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListReducer
import com.example.newmvi.ui.fragments.todoList.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    interactors: @JvmSuppressWildcards Set<BaseInteractor<TodoListEvent, TodoListState>>,
    private val router: BaseRouter,
) : BaseViewModel<TodoListEvent, TodoListState>(
    interactors = interactors,
    reducer = TodoListReducer()
) {

    fun loadTodoList() {
        setEvent(TodoListEvent.LoadTodoList)
    }

    fun navigateToTodoCreatorFragment() {
        router.execute(NavigationCommand.Navigate(TodoCreatorScreen()))
    }

    fun navigateToTodoEditorFragment(todo: Todo) {
        router.execute(NavigationCommand.Navigate(TodoEditorScreen(todo)))
    }

}