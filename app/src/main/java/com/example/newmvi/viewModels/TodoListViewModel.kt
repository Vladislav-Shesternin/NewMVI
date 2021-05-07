package com.example.newmvi.viewModels

import com.example.newmvi.domain.interactors.GetTodoListInteractor
import com.example.newmvi.mvi.BaseViewModel
import com.example.newmvi.ui.custom.todoRecycleView.Command
import com.example.newmvi.ui.custom.todoRecycleView.Router
import com.example.newmvi.ui.fragments.todoList.ToDoListScreen
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListReducer
import com.example.newmvi.ui.fragments.todoList.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    getTodoList: GetTodoListInteractor,
private val router: Router
) : BaseViewModel<TodoListEvent, TodoListState>(
    interactors = setOf(getTodoList),
    reducer = TodoListReducer()
) {

    fun getTodoList() {
        setEvent(TodoListEvent.GetTodoList)
    }

    fun navigateToTodo(arg: Int) {
        router.execute(Command.Navigate(ToDoListScreen(arg)))
    }

}