package com.example.newmvi.db.room.dao

import androidx.room.*
import com.example.newmvi.db.room.entities.TodoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(todo: TodoEntity)

    @Update
    fun update(todo: TodoEntity)

    @Query("SELECT * FROM todo_table")
    fun getAllTodo(): Flow<List<TodoEntity>>

}