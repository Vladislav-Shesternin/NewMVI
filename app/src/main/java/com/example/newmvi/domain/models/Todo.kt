package com.example.newmvi.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Todo(
    var todoId: UUID,
    var todoText: String,
    var todoColor: Int,
) : Parcelable
