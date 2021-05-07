package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.mvi.BaseEvent

sealed class TodoCreatorEvent : BaseEvent {
    data class GetColor(val color: Int): TodoCreatorEvent()
    data class GotColor(val color: Int): TodoCreatorEvent()
}
