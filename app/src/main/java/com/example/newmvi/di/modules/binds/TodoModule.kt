package com.example.newmvi.di.modules.binds

import com.example.newmvi.domain.repositories.TodoRepo
import com.example.newmvi.domain.repositories.TodoRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class TodoModule {

    @Binds
    abstract fun bindTodoRepo(impl: TodoRepoImpl): TodoRepo

}