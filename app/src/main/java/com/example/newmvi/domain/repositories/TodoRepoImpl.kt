package com.example.newmvi.domain.repositories

import android.util.Log
import com.example.newmvi.db.firebaseRealtime.models.TodoModel
import com.example.newmvi.db.firebaseRealtime.models.asTodoEntity
import com.example.newmvi.db.room.dao.TodoDao
import com.example.newmvi.db.room.entities.asTodoEntity
import com.example.newmvi.db.room.entities.asTodoList
import com.example.newmvi.domain.models.Todo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TodoRepoImpl @Inject constructor(
    private val todoDao: TodoDao,
) : TodoRepo {

    private val TAG = this::class.simpleName

    private val database: DatabaseReference = Firebase.database.reference.child("Todo")

    override fun loadTodoList() {

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot.children.forEach {
                    val model = it.getValue<TodoModel>() ?: return@forEach
                    model.id = it.key!!

                    CoroutineScope(Dispatchers.IO).launch {
                        todoDao.insert(model.asTodoEntity())
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(TAG, "onCancelled: ${error.message}")
            }
        })
    }

    override fun getTodoColor(color: Int): Int = color

    override fun insertTodo(todo: Todo) {
        todoDao.insert(todo.asTodoEntity())
    }

    override fun updateTodo(todo: Todo) {
        todoDao.update(todo.asTodoEntity())
    }

    override fun getAllTodo(): Flow<List<Todo>> {
        return todoDao.getAllTodo().map { it.asTodoList() }
    }


}