package com.example.newmvi.data

import android.util.Log
import com.example.newmvi.db.firebaseRealtime.models.TodoModel
import com.example.newmvi.db.firebaseRealtime.models.asTodo
import com.example.newmvi.db.firebaseRealtime.models.asTodoModel
import com.example.newmvi.domain.interactors.todoList.printVlad
import com.example.newmvi.domain.models.Todo
import com.example.newmvi.domain.repos.TodoRepo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class TodoFirebaseRealtimeRepoImpl @Inject constructor(
    private val database: DatabaseReference
) : TodoRepo {

    private val TAG = this::class.simpleName

    override fun getTodoListFlow(): Flow<List<Todo>> {
        val todoFlow = MutableSharedFlow<List<Todo>>(extraBufferCapacity = 1)

        database.child("Todo").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val todoList = mutableListOf<Todo>()

                snapshot.children.forEach {
                    val todoModel = it.getValue<TodoModel>() ?: return@forEach
                    val id = UUID.fromString(it.key!!)
                    todoList.add(todoModel.asTodo(id))
                }

                todoFlow.tryEmit(todoList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i(TAG, "onCancelled: ${error.message}")
            }
        })

        return todoFlow
    }

    override suspend fun insertTodo(todo: Todo): Boolean {
        val deferredResponse = CompletableDeferred<Boolean>()

        database.child("Todo/${todo.todoId}").setValue(todo.asTodoModel()).addOnCompleteListener {
            if (it.isSuccessful) {
                deferredResponse.complete(true)
            } else {
                deferredResponse.complete(false)
            }
        }
        return deferredResponse.await()
    }

    override suspend fun insertTodoList(todoList: List<Todo>) {
        throw UnsupportedOperationException("${this::class.simpleName} not intended for insertTodoList")
    }

    override suspend fun updateTodo(todo: Todo): Boolean {
        val deferredResponse = CompletableDeferred<Boolean>()

        val childUpdate = mapOf(todo.todoId.toString() to todo.asTodoModel())
        database.child("Todo").updateChildren(childUpdate).addOnCompleteListener {
            if (it.isSuccessful) {
                deferredResponse.complete(true)
            } else {
                deferredResponse.complete(false)
            }
        }
        return deferredResponse.await()
    }

    override suspend fun getTodoList(): List<Todo> {
        val deferredTodoList = CompletableDeferred<List<Todo>>()

        database.child("Todo").get().addOnSuccessListener { snapshot ->
            val todoList = mutableListOf<Todo>()
            snapshot.children.forEach { data ->
                val todoModel = data.getValue<TodoModel>() ?: return@forEach
                val id = UUID.fromString(data.key!!)
                todoList.add(todoModel.asTodo(id))
            }
            deferredTodoList.complete(todoList)
        }

        return deferredTodoList.await()
    }


    override suspend fun getColor(color: String) = color
}