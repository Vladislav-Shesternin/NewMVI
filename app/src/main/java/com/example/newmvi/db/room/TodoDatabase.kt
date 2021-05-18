package com.example.newmvi.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newmvi.db.room.convertors.UUIDConvertor
import com.example.newmvi.db.room.dao.TodoDao
import com.example.newmvi.db.room.entities.TodoEntity

@Database(entities = [TodoEntity::class], version = 2, exportSchema = false)
@TypeConverters(UUIDConvertor::class)
abstract class TodoDatabase : RoomDatabase() {

    abstract val todoDao: TodoDao

}