package com.example.newmvi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newmvi.mvi.BaseEvent
import com.example.newmvi.mvi.BaseInteractor
import com.example.newmvi.mvi.BaseReducer
import com.example.newmvi.mvi.BaseState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event : BaseEvent, State : BaseState>(
    private val interactors: Set<BaseInteractor<Event, State>>,
    private val reducer: BaseReducer<Event, State>
) : ViewModel() {

    private val currentState: State
        get() = _state.value

    private val _state: MutableStateFlow<State> = MutableStateFlow(reducer.initialState)
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    init {
        subscribeEvents()
    }

    protected fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    private fun subscribeEvents() {

        interactors.forEach { interactor->
            viewModelScope.launch {
                interactor(_event, _state).collect {
                    setEvent(it)
                }
            }
        }

        viewModelScope.launch {
            _event.collect{
                _state.value = reducer.reduce(it, currentState)
            }
        }

    }

}