package com.example.todolist.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.model.TaskModel
import com.example.todolist.repositories.AppRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class AppViewModel: ViewModel() {


    private val appRepository = AppRepository.get()

    var appTask = appRepository.getTask()


    var task: TaskModel? = null
    var selectedItemMutableLiveDate = MutableLiveData<TaskModel>()


    fun addTask(taskTitle: String, dueDate:String, description: String, completed: Boolean){

        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = dateFormat.format(Date())
        viewModelScope.launch {
            appRepository.addTask(TaskModel(taskTitle, dueDate,description, currentDate, completed))
        }
    }

    fun updateTask(taskModel: TaskModel) {
        viewModelScope.launch {
            appRepository.updateTask(taskModel)
        }
    }

    fun deleteTask(taskModel: TaskModel){
        viewModelScope.launch {
            appRepository.deleteTask(taskModel)
        }
    }

}