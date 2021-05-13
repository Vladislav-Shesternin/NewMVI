package com.example.newmvi.domain.interactors.todoCreator

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import javax.inject.Inject

class TodoCreatorGetAllTodoFromDB @Inject constructor(
    private val repo: TodoRepo
) {

    operator fun invoke(): List<Todo> {
        return repo.getAllTodoFromDB()
    }

}