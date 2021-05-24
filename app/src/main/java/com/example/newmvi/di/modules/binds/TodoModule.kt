package com.example.newmvi.di.modules.binds

import com.example.newmvi.di.modules.qualifiers.todo.QualTodoCacheAndLoadRepo
import com.example.newmvi.di.modules.qualifiers.todo.QualTodoDatabaseRepo
import com.example.newmvi.di.modules.qualifiers.todo.QualTodoFirebaseRealtimeRepo
import com.example.newmvi.di.modules.qualifiers.todo.QualTodoRepo
import com.example.newmvi.domain.repositories.todo.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class TodoModule {

    @QualTodoRepo
    @Binds
    abstract fun bindTodoRepo(impl: TodoRepoImpl): TodoRepo

    @QualTodoDatabaseRepo
    @Binds
    abstract fun bindTodoDatabaseRepo(impl: TodoDatabaseRepoImpl): TodoRepo

    @QualTodoFirebaseRealtimeRepo
    @Binds
    abstract fun bindTodoFirebaseRealtimeRepo(impl: TodoFirebaseRealtimeRepoImpl): TodoRepo

    @QualTodoCacheAndLoadRepo
    @Binds
    abstract fun bindTodoCacheAndLoadRepo(impl: TodoCacheAndLoadRepoImpl): TodoRepo

}