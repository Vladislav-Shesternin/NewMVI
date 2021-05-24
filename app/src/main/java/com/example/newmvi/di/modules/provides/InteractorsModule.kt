package com.example.newmvi.di.modules.provides

import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorLoadTodoColorInteractor
import com.example.newmvi.domain.interactors.todoCreator.TodoCreatorInsertTodoInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorLoadTodoColorInteractor
import com.example.newmvi.domain.interactors.todoEditor.TodoEditorUpdateTodoInteractor
import com.example.newmvi.domain.interactors.todoList.TodoListGetTodoListInteractor
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorEvent
import com.example.newmvi.ui.fragments.todoCreator.TodoCreatorState
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorEvent
import com.example.newmvi.ui.fragments.todoEditor.TodoEditorState
import com.example.newmvi.ui.fragments.todoList.TodoListEvent
import com.example.newmvi.ui.fragments.todoList.TodoListState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object InteractorsModule {

    @Provides
    fun provideSetBaseInteractorList(
        getTodoList: TodoListGetTodoListInteractor,
    ): Set<BaseInteractor<TodoListEvent, TodoListState>> {
        return setOf(
            getTodoList
        )
    }

    @Provides
    fun provideSetBaseInteractorEditor(
        getTodoColor: TodoEditorLoadTodoColorInteractor,
        updateTodo: TodoEditorUpdateTodoInteractor,
    ): Set<BaseInteractor<TodoEditorEvent, TodoEditorState>> {
        return setOf(
            getTodoColor,
            updateTodo,
        )
    }

    @Provides
    fun provideSetBaseInteractorCreator(
        getTodoColor: TodoCreatorLoadTodoColorInteractor,
        insertTodo: TodoCreatorInsertTodoInteractor,
    ): Set<BaseInteractor<TodoCreatorEvent, TodoCreatorState>> {
        return setOf(
            getTodoColor,
            insertTodo,
        )
    }

}