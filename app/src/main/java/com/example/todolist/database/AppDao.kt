package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.database.model.TaskModel

@Dao
interface AppDao {

    @Insert
    suspend fun addTask(taskModel: TaskModel)

    @Query("SELECT * FROM taskmodel")
    fun getTask(): LiveData<List<TaskModel>>

    @Update
    suspend fun updateTask(taskModel: TaskModel)


    @Delete
    suspend fun deleteTask(taskModel: TaskModel)

}