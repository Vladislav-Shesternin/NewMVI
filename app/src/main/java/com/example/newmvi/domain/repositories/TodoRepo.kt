package com.example.newmvi.domain.repositories

import com.example.newmvi.domain.models.Todo

interface TodoRepo {

    fun getTodoList(): List<Todo>

    fun getTodoColor(color: Int): Int

    fun insertTodoList(todoList: List<Todo>)

    fun insertTodo(todo: Todo)

    fun updateTodo(todo: Todo)

    fun getAllTodo(): List<Todo>
}