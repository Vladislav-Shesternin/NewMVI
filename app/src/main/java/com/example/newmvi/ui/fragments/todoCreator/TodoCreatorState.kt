package com.example.newmvi.ui.fragments.todoCreator

import com.example.newmvi.mvi.BaseState

data class TodoCreatorState(
    val isLoading: Boolean,
    val color: Int
): BaseState