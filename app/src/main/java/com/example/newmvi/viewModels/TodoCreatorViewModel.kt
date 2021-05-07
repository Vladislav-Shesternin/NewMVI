package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.GetTodoColorInteractor
import com.example.newmvi.mvi.BaseViewModel
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorReducer
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoCreatorViewModel @Inject constructor(
    getTodoColor: GetTodoColorInteractor
) : BaseViewModel<TodoCreatorEvent, TodoCreatorState>(
    interactors = setOf(getTodoColor),
    reducer = TodoCreatorReducer()
) {

    val getTodoColor: (Int) -> Unit = {
        setEvent(TodoCreatorEvent.GetColor(it))
    }

}