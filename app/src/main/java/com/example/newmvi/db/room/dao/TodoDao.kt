package com.example.newmvi.db.room.dao

import androidx.room.*
import com.example.newmvi.db.room.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(todoList: List<TodoEntity>)

    @Update
    suspend fun update(todo: TodoEntity)

    @Query("SELECT * FROM todo_table")
    suspend fun getAllTodo(): List<TodoEntity>

    @Query("SELECT * FROM todo_table")
    fun getFlowTodo(): Flow<List<TodoEntity>>

}