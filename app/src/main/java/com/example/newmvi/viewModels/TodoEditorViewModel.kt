package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.todoEditor.TodoEditorGetAllTodoFromDB
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorGetTodoColorInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorInsertTodoToDBInPosition
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.base.BaseViewModel
import com.example.newmvi.navigation.NavigationCommand
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorReducer
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoEditorViewModel @Inject constructor(
    getTodoColor: TodoEditorGetTodoColorInteractor,
    private val insertTodoToDBInPosition: TodoEditorInsertTodoToDBInPosition,
    private val getAllTodoFromDB: TodoEditorGetAllTodoFromDB,
    private val router: BaseRouter
) : BaseViewModel<TodoEditorEvent, TodoEditorState>(
    interactors = setOf(getTodoColor),
    reducer = TodoEditorReducer()
) {

    val getTodoColor: (color: Int) -> Unit = {
        setEvent(TodoEditorEvent.GetColor(it))
    }

    fun addTodoToDBInPosition(todo: Todo, position: Int) {
        insertTodoToDBInPosition(todo, position)
    }

    fun getAllTodoFromDb(): List<Todo> {
        return getAllTodoFromDB()
    }

    fun navigateBack() {
        router.execute(NavigationCommand.Back)
    }

}