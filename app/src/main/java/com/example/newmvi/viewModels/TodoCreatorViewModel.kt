package com.example.newmvi.viewModels

import com.example.newmvi.base.BaseViewModel
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.navigation.NavigationCommand
import com.example.newmvi.todoAmount
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorReducer
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoCreatorViewModel @Inject constructor(
    interactors: @JvmSuppressWildcards Set<BaseInteractor<TodoCreatorEvent, TodoCreatorState>>,
    private val router: BaseRouter,
) : BaseViewModel<TodoCreatorEvent, TodoCreatorState>(
    interactors = interactors,
    reducer = TodoCreatorReducer(),
) {

    val loadColor: (color: Int) -> Unit = {
        setEvent(TodoCreatorEvent.LoadColor(it))
    }

    fun insertTodoInDb(todo: Todo) {
        ++todoAmount
        setEvent(TodoCreatorEvent.InsertTodo(todo))
    }

    fun navigateBack() {
        router.execute(NavigationCommand.Back)
    }

}