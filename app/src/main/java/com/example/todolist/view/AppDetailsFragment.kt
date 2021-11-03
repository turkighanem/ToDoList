package com.example.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.model.TaskModel


class AppDetailsFragment : Fragment() {

    private val appViewModel: AppViewModel by activityViewModels()
    private lateinit var selectedTask: TaskModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_app_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskTitleEditText: EditText = view.findViewById(R.id.details_title_edittext)
        val descriptionEditText: EditText = view.findViewById(R.id.details_description_edittext)
        val dueDateEditText: EditText = view.findViewById(R.id.details_duedate_edittext)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
        val currentDate: TextView = view.findViewById(R.id.textView_date)


        val saveButton: Button = view.findViewById(R.id.details_save_button)


        appViewModel.selectedItemMutableLiveDate.observe(viewLifecycleOwner, Observer {
            it?.let { task ->
                taskTitleEditText.setText(task.titleTask)
                descriptionEditText.setText(task.description)
                dueDateEditText.setText("${task.dueDate}")
                currentDate.text = task.creationDate
                selectedTask = task
            }
        })

        saveButton.setOnClickListener {
            var tasktitle = taskTitleEditText.text.toString()
            var description = descriptionEditText.text.toString()
            var dueDate = dueDateEditText.text.toString()

            selectedTask.titleTask = tasktitle
            selectedTask.description = description
            selectedTask.dueDate = dueDate

            appViewModel.updateTask(selectedTask)
            findNavController().popBackStack()

        }

        deleteButton.setOnClickListener {
            appViewModel.deleteTask(selectedTask)

            findNavController().popBackStack()
        }

    }
}