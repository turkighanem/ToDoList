package com.example.todolist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.database.model.TaskModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AppListFragment : Fragment() {

    private val appTasks = mutableListOf<TaskModel>()
    private val appViewModel: AppViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_app_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val appRecyclerView: RecyclerView = view.findViewById(R.id.app_recyclerview)
        val addFloatingActionButton: FloatingActionButton = view.findViewById(R.id.add_button)

        val appAdapter = AppAdapter(appTasks,appViewModel)

        appRecyclerView.adapter = appAdapter


        appViewModel.appTask.observe(viewLifecycleOwner, Observer {
            it?.let { tasks ->
                appTasks.clear()
                appTasks.addAll(tasks)
                appAdapter.notifyDataSetChanged()
            }
        })

        addFloatingActionButton.setOnClickListener {

            findNavController().navigate(R.id.action_appListFragment_to_addTaskFragment)
        }
    }
}