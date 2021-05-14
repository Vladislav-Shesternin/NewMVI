package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorGetAllTodoFromDBInteractor
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorGetTodoColorInteractor
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorInsertTodoToDBInteractor
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.base.BaseViewModel
import com.example.newmvi.navigation.NavigationCommand
import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorReducer
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoCreatorViewModel @Inject constructor(
    getTodoColor: TodoCreatorGetTodoColorInteractor,
    getAllTodoFromDB: TodoCreatorGetAllTodoFromDBInteractor,
    insertTodoToDB: TodoCreatorInsertTodoToDBInteractor,
    private val router: BaseRouter
) : BaseViewModel<TodoCreatorEvent, TodoCreatorState>(
    interactors = setOf(getTodoColor, getAllTodoFromDB, insertTodoToDB),
    reducer = TodoCreatorReducer()
) {

    val getTodoColor: (color: Int) -> Unit = {
        setEvent(TodoCreatorEvent.GetColor(it))
    }

    fun getAllTodoFromDb() {
        setEvent(TodoCreatorEvent.GetAllTodo)
    }

    fun addTodoToDB(todo: Todo) {
        setEvent(TodoCreatorEvent.InsertTodo(todo))
    }

    fun navigateBack() {
        router.execute(NavigationCommand.Back)
    }

}