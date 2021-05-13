package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.todoList.TodoListGetTodoListInteractor
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseViewModel
import com.example.newmvi.navigation.BaseCommand
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorScreen
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorScreen
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListReducer
import com.example.newmvi.ui.fragments.todoList.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    getTodoList: TodoListGetTodoListInteractor,
    private val router: BaseRouter,
) : BaseViewModel<TodoListEvent, TodoListState>(
    interactors = setOf(getTodoList),
    reducer = TodoListReducer()
) {

    fun getTodoList() {
        setEvent(TodoListEvent.GetTodoList)
    }

    fun navigateToTodoCreatorFragment() {
        router.execute(BaseCommand.Navigate(TodoCreatorScreen()))
    }

    fun navigateToTodoEditorFragment(todo: Todo, position: Int) {
        router.execute(BaseCommand.Navigate(TodoEditorScreen(todo, position)))
    }

}