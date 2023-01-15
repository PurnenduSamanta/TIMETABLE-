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
import com.purnendu.timetable.databinding.FragmentSundayBinding
import com.purnendu.timetable.model.Task
import com.purnendu.timetable.ui.adapter.TaskRecyclerViewAdapter
import com.purnendu.timetable.ui.viewPagerFragments.viewmodel.ViewPagerFragmentsViewModel
import com.purnendu.timetable.ui.viewPagerFragments.viewmodel.ViewPagerFragmentsViewModelFactory


class Sunday : Fragment() {


    private var _binding: FragmentSundayBinding? = null
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
        _binding =FragmentSundayBinding.inflate(inflater, container, false)
        adapter = TaskRecyclerViewAdapter(requireContext()) { id, position ->
            viewModel.deleteTask(id)

        }
        binding.recyclerViewSunday.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSunday.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllTask().observe(viewLifecycleOwner) {
            val taskList = it.filter { taskModel ->
                taskModel.selectedDate==DAY.SUNDAY
            }
            val sundayTaskList = mutableListOf<Task>()
            for (task in taskList)
                sundayTaskList.add(Task(task.taskId,task.taskName, task.taskDescription))
            if(sundayTaskList.isEmpty())
                binding.noTaskImage.visibility=View.VISIBLE
            else
                binding.noTaskImage.visibility=View.GONE
            adapter.setTaskList(sundayTaskList)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}