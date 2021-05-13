package com.example.newmvi.domain.interactors.todoEditor

import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repositories.TodoRepo
import javax.inject.Inject

class TodoEditorInsertTodoToDBInPosition @Inject constructor(
    private val repo: TodoRepo
) {

    operator fun invoke(todo: Todo, position: Int) {
        repo.insertTodoToDBInPosition(todo, position)
    }

}