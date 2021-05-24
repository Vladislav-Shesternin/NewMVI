package com.example.newmvi.di.modules.qualifiers.todo

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class QualTodoRepo

@Qualifier
@Retention
annotation class QualTodoDatabaseRepo

@Qualifier
@Retention
annotation class QualTodoFirebaseRealtimeRepo

@Qualifier
@Retention
annotation class QualTodoCacheAndLoadRepo