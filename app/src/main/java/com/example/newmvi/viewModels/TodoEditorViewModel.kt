package com.example.newmvi.viewModels

import com.example.newmvi.base.BaseViewModel
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.navigation.NavigationCommand
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorReducer
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoEditorViewModel @Inject constructor(
    interactors: @JvmSuppressWildcards Set<BaseInteractor<TodoEditorEvent, TodoEditorState>>,
    private val router: BaseRouter,
) : BaseViewModel<TodoEditorEvent, TodoEditorState>(
    interactors = interactors,
    reducer = TodoEditorReducer()
) {

    val getColor: (color: String) -> Unit = {
        setEvent(TodoEditorEvent.LoadColor(it))
    }

    fun updateTodo(todo: Todo) {
        setEvent(TodoEditorEvent.UpdateTodo(todo))
    }

    fun navigateBack() {
        router.execute(NavigationCommand.Back)
    }

}