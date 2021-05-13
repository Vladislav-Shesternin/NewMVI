package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorGetAllTodoFromDB
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorGetTodoColorInteractor
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorInsertTodoToDB
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
    private val getAllTodoFromDB: TodoCreatorGetAllTodoFromDB,
    private val insertTodoToDB: TodoCreatorInsertTodoToDB,
    private val router: BaseRouter
) : BaseViewModel<TodoCreatorEvent, TodoCreatorState>(
    interactors = setOf(getTodoColor),
    reducer = TodoCreatorReducer()
) {

    val getTodoColor: (color: Int) -> Unit = {
        setEvent(TodoCreatorEvent.GetColor(it))
    }

    fun getAllTodoFromDb(): List<Todo> {
        return getAllTodoFromDB()
    }

    fun addTodoToDB(todo: Todo) {
        insertTodoToDB(todo)
    }

    fun navigateBack() {
        router.execute(NavigationCommand.Back)
    }

}