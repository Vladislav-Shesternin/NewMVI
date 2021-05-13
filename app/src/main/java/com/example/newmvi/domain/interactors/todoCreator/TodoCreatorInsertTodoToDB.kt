package com.example.newmvi.domain.interactors.todoCreator

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import javax.inject.Inject

class TodoCreatorInsertTodoToDB @Inject constructor(
    private val repo: TodoRepo
) {

    operator fun invoke(todo: Todo) {
        repo.insertTodoToDB(todo)
    }

}