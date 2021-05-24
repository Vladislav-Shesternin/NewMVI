package com.example.newmvi.ui.custom.todoRecycleView

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newmvi.databinding.ItemTodoBinding
import com.example.newmvi.domain.models.Todo

class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }
}

class TodoAdapter(
    private val onItemClick: (todo: Todo) -> Unit
) : ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).also { layoutInflater ->
            return ViewHolder(ItemTodoBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    class ViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val TAG = this::class.simpleName

        fun bind(item: Todo, onItemClick: (todo: Todo) -> Unit) {
            binding.tvTodo.apply {

                text = item.todoText
                setBackgroundColor(Color.parseColor(item.todoColor))

                setOnClickListener { onItemClick(item) }

            }
        }
    }
}