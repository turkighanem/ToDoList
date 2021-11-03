package com.example.todolist.database
import androidx.room.Database
import com.example.todolist.database.model.TaskModel
import androidx.room.RoomDatabase

@Database(entities = [TaskModel::class], version = 5)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}