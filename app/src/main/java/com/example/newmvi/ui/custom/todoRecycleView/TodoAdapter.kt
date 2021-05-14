package com.example.newmvi.ui.custom.todoRecycleView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newmvi.databinding.ItemTodoBinding
import com.example.newmvi.domain.models.Todo

// ------------------------------------------------------------
//                  DiffUtil
// ------------------------------------------------------------
class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
        return oldItem == newItem
    }

}

// ------------------------------------------------------------
//                  Adapter
// ------------------------------------------------------------
class TodoAdapter : ListAdapter<Todo, TodoAdapter.ViewHolder>(TodoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).also { layoutInflater ->
            return ViewHolder(ItemTodoBinding.inflate(layoutInflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    var onItemClick: (TextView, position: Int) -> Unit = { _, _ -> }

    // ------------------------------------------------------------
    //                  ViewHolder
    // ------------------------------------------------------------
    inner class ViewHolder(
        private val binding: ItemTodoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Todo) {
            binding.tvTodo.apply {

                text = item.todoText
                setBackgroundColor(item.todoColor)

                setOnClickListener {
                    onItemClick.invoke(this, adapterPosition)
                }

            }
        }
    }
}