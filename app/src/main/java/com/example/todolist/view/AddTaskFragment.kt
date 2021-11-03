package com.example.todolist.view

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import java.util.*


class AddTaskFragment : Fragment() {


    private val appViewModel: AppViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val taskTitleEditText: EditText = view.findViewById(R.id.task_title_edittext)
        val descriptionEditText: EditText = view.findViewById(R.id.description_edittext)
        val dueDateEditText: EditText = view.findViewById(R.id.dueDate_edittext)
        val completedCheckbox: CheckBox = view.findViewById(R.id.completed_checkbox)



        val saveButton: Button = view.findViewById(R.id.details_save_button)


        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)



        dueDateEditText.setOnClickListener(){
            val dpd = DatePickerDialog(view.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                // Display Selected date in TextView
                dueDateEditText.setText("" + day + "/" + (month+1) + "/" + year)
            }, year, month, day)
            dpd.show()

        }


        saveButton.setOnClickListener {
            val taskTile = taskTitleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val dueDate = dueDateEditText.text.toString()

            val completed = completedCheckbox.isChecked


            if (taskTile.isNotEmpty() && description.isNotEmpty()){

               appViewModel.addTask(taskTile, description,dueDate ,completed)
                findNavController().popBackStack()

            }

        }

    }
}