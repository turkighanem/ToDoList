package com.example.todolist.repositories

import android.content.Context
import androidx.room.Room
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.model.TaskModel
import java.lang.Exception


private const val DATABASE_NAME = "app-database"
class AppRepository(context: Context) {

    private val database: AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()

    private val appDao = database.appDao()
    fun getTask() = appDao.getTask()

    suspend fun addTask(taskModel: TaskModel) = appDao.addTask(taskModel)
    suspend fun updateTask(taskModel: TaskModel) = appDao.updateTask(taskModel)
    suspend fun deleteTask(taskModel: TaskModel) = appDao.deleteTask(taskModel)

    companion object {
        private var instance: AppRepository? = null

        fun init(context: Context) {
            if (instance == null)
                instance = AppRepository(context)
        }

        fun get(): AppRepository {
            return instance ?: throw Exception("App Repository must be initialized")
        }
    }



}