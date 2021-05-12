package com.example.newmvi.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Todo(
    var todoText: String,
    var todoColor: Int
) : Parcelable
