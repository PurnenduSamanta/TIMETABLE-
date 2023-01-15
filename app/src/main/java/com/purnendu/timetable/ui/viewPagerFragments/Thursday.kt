package com.purnendu.timetable.ui.viewPagerFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.purnendu.timetable.DAY
import com.purnendu.timetable.Repository
import com.purnendu.timetable.database.Database
import com.purnendu.timetable.databinding.FragmentThursdayBinding
import com.purnendu.timetable.model.Task
import com.purnendu.timetable.ui.adapter.TaskRecyclerViewAdapter
import com.purnendu.timetable.ui.viewPagerFragments.viewmodel.ViewPagerFragmentsViewModel
import com.purnendu.timetable.ui.viewPagerFragments.viewmodel.ViewPagerFragmentsViewModelFactory


class Thursday : Fragment() {

    private var _binding: FragmentThursdayBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TaskRecyclerViewAdapter
    private lateinit var viewModel: ViewPagerFragmentsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository(Database.getDataBase(requireContext()))
        viewModel = ViewModelProvider(
            this,
            ViewPagerFragmentsViewModelFactory(repository)
        )[ViewPagerFragmentsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentThursdayBinding.inflate(inflater, container, false)
        adapter = TaskRecyclerViewAdapter(requireContext()) { id, position ->
            viewModel.deleteTask(id)

        }
        binding.recyclerViewThursday.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewThursday.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllTask().observe(viewLifecycleOwner) {
            val taskList = it.filter { taskModel ->
                taskModel.selectedDate==DAY.THURSDAY
            }
            val thursdayTaskList = mutableListOf<Task>()
            for (task in taskList)
                thursdayTaskList.add(Task(task.taskId,task.taskName, task.taskDescription))

            if(thursdayTaskList.isEmpty())
                binding.noTaskImage.visibility=View.VISIBLE
            else
                binding.noTaskImage.visibility = View.GONE
            adapter.setTaskList(thursdayTaskList)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}