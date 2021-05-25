package com.example.newmvi.di.provides

import android.content.Context
import androidx.room.Room
import com.example.newmvi.db.room.TodoDatabase
import com.example.newmvi.db.room.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            "todo_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao
    }

}