package com.example.newmvi.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val event = _event.asSharedFlow()

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