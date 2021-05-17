package com.example.newmvi.domain.interactors.todoCreator

import com.example.newmvi.db.dao.TodoDao
import com.example.newmvi.db.entities.asTodoEntity
import com.example.newmvi.domain.repositories.TodoRepo
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoCreatorInsertTodoInDbInteractor @Inject constructor(
    private val todoDao: TodoDao
) : BaseInteractor<TodoCreatorEvent, TodoCreatorState> {

    override fun invoke(
        event: Flow<TodoCreatorEvent>,
        state: Flow<TodoCreatorState>
    ): Flow<TodoCreatorEvent> {
        return event.filterIsInstance<TodoCreatorEvent.InsertTodo>()
            .map {
                todoDao.insert(it.todo.asTodoEntity())
                TodoCreatorEvent.InsertedTodo
            }.flowOn(Dispatchers.Default)
    }

}