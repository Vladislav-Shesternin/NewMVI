package com.example.newmvi.ui.custom.todoRecycleView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newmvi.domain.models.Todo

class TodoRecycleView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    private val TAG = this::class.simpleName

    private var adapterTodo: TodoAdapter? = null

    fun initAdapter(adapter: TodoAdapter) {
        adapterTodo = adapter
        this.adapter = adapter
    }

    fun setItemList(todoList: List<Todo>) {
        Log.i(TAG, "setItemList: $adapterTodo")
        adapterTodo?.submitList(todoList)
    }

}