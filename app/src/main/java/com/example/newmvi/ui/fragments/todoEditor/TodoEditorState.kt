package com.example.newmvi.ui.fragments.todoEditor

import com.example.newmvi.mvi.BaseState

data class TodoEditorState(
    val isLoading: Boolean,
    val color: Int,
) : BaseState