package com.example.todolist.database.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskModel(
    var titleTask: String,
    var description: String,
    var dueDate: String,
    val creationDate:String,
    val completed: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)