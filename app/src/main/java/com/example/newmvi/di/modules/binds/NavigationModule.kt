package com.example.newmvi.di.modules.binds

import com.example.newmvi.navigation.BaseRouter
import com.example.newmvi.navigation.BaseRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindRouter(impl: BaseRouterImpl): BaseRouter

}