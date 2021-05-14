package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.todoEditor.TodoEditorGetAllTodoFromDBInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorGetTodoColorInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorInsertTodoToDBInPositionInteractor
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
    insertTodoToDBInPosition: TodoEditorInsertTodoToDBInPositionInteractor,
    getAllTodoFromDB: TodoEditorGetAllTodoFromDBInteractor,
    private val router: BaseRouter
) : BaseViewModel<TodoEditorEvent, TodoEditorState>(
    interactors = setOf(getTodoColor, insertTodoToDBInPosition, getAllTodoFromDB),
    reducer = TodoEditorReducer()
) {

    val getTodoColor: (color: Int) -> Unit = {
        setEvent(TodoEditorEvent.GetColor(it))
    }

    fun addTodoToDBInPosition(todo: Todo, position: Int) {
        setEvent(TodoEditorEvent.InsertTodoInPosition(todo, position))
    }

    fun getAllTodoFromDb() {
        setEvent(TodoEditorEvent.GetAllTodo)
    }

    fun navigateBack() {
        router.execute(NavigationCommand.Back)
    }

}