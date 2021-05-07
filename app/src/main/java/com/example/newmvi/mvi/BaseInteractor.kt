package com.example.newmvi.mvi

import kotlinx.coroutines.flow.Flow

interface BaseInteractor<Event : BaseEvent, State : BaseState> {

    operator fun invoke(event: Flow<Event>, state: Flow<State>): Flow<Event>

}