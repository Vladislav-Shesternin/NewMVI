package com.example.newmvi.di.modules.provides

import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorGetAllTodoFromDBInteractor
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorGetTodoColorInteractor
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorInsertTodoToDBInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorLoadTodoColorInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorUpdateTodoInDbInteractor
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object InteractorsModule {

    @Provides
    fun provideSetBaseInteractorEditor(
        getTodoColor: TodoEditorLoadTodoColorInteractor,
        updateTodoInDb: TodoEditorUpdateTodoInDbInteractor,
    ): Set<BaseInteractor<TodoEditorEvent, TodoEditorState>> {
        return setOf(
            getTodoColor,
            updateTodoInDb,
        )
    }

    @Provides
    fun provideSetBaseInteractorCreator(
        getTodoColor: TodoCreatorGetTodoColorInteractor,
        getAllTodoFromDB: TodoCreatorGetAllTodoFromDBInteractor,
        insertTodoToDB: TodoCreatorInsertTodoToDBInteractor,
    ): Set<BaseInteractor<TodoCreatorEvent, TodoCreatorState>> {
        return setOf(
            getTodoColor,
            getAllTodoFromDB,
            insertTodoToDB,
        )
    }

}