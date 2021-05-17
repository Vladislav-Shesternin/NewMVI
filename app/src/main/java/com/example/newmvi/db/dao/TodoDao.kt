package com.example.newmvi.db.dao

import androidx.room.*
import com.example.newmvi.db.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertList(todoList: List<TodoEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todoList: TodoEntity)

    @Update
    fun update(todo: TodoEntity)

    @Query("SELECT * FROM todo_table")
    fun getAllTodo(): List<TodoEntity>
}