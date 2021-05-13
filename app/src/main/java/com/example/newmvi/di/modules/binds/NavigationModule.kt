package com.example.newmvi.di.modules.binds

import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.navigation.NavigationComponentRouter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class NavigationModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindRouter(impl: NavigationComponentRouter): BaseRouter

}