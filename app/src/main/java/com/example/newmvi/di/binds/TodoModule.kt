package com.example.newmvi.di.binds

import com.example.newmvi.db.todo.QualTodoDatabaseRepo
import com.example.newmvi.db.todo.QualTodoFirebaseRealtimeRepo
import com.example.newmvi.data.TodoCacheAndLoadRepoImpl
import com.example.newmvi.data.TodoDatabaseRepoImpl
import com.example.newmvi.data.TodoFirebaseRealtimeRepoImpl
import com.example.newmvi.domain.repos.TodoRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class TodoModule {

    @Binds
    abstract fun bindTodoCacheAndLoadRepo(impl: TodoCacheAndLoadRepoImpl): TodoRepo

    @QualTodoDatabaseRepo
    @Binds
    abstract fun bindTodoDatabaseRepo(impl: TodoDatabaseRepoImpl): TodoRepo

    @QualTodoFirebaseRealtimeRepo
    @Binds
    abstract fun bindTodoFirebaseRealtimeRepo(impl: TodoFirebaseRealtimeRepoImpl): TodoRepo

}