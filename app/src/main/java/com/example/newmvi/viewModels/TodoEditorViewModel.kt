package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.todoEditor.TodoEditorGetTodoColorInteractor
import com.example.newmvi.mvi.BaseViewModel
import com.example.newmvi.navigation.BaseCommand
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorReducer
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoEditorViewModel @Inject constructor(
    getTodoColor: TodoEditorGetTodoColorInteractor,
    private val router: BaseRouter
) : BaseViewModel<TodoEditorEvent, TodoEditorState>(
    interactors = setOf(getTodoColor),
    reducer = TodoEditorReducer()
) {

    val getTodoColor: (color: Int) -> Unit = {
        setEvent(TodoEditorEvent.GetColor(it))
    }

    fun navigateBack() {
        router.execute(BaseCommand.Back)
    }

}