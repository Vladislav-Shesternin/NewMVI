package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.mvi.BaseEvent

sealed class TodoEditorEvent: BaseEvent {
    data class GetColor(val color: Int): TodoEditorEvent()
    data class GotColor(val color: Int): TodoEditorEvent()
}