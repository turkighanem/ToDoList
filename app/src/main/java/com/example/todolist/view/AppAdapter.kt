package com.example.todolist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.model.TaskModel

class AppAdapter
    (val tasks: List<TaskModel>, val viewModel: AppViewModel):
    RecyclerView.Adapter<AppAdapter.AppViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppAdapter.AppViewHolder {

        return AppViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: AppAdapter.AppViewHolder, position: Int) {

        val task = tasks[position]

        holder.taskTitleTextView.text = task.titleTask
        holder.dueDateTextView.text = task.dueDate
        holder.completed.isChecked = task.completed

        holder.itemView.setOnClickListener { view ->

            viewModel.selectedItemMutableLiveDate.postValue(task)
            view.findNavController().navigate(R.id.action_appListFragment_to_appDetailsFragment)
        }

        holder.completed.setOnClickListener {
            holder.completed.isChecked = task.completed
            viewModel.updateTask(task)
        }
    }


    override fun getItemCount(): Int {
        return tasks.size
    }

    class AppViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitleTextView: TextView = view.findViewById(R.id.details_title_edittext)
        val dueDateTextView: TextView = view.findViewById(R.id.dueDate_textview)
        val completed: CheckBox = view.findViewById(R.id.completed2_checkbox)
    }
}