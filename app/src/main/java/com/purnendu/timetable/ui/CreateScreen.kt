package com.purnendu.timetable.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.purnendu.timetable.DAY
import com.purnendu.timetable.R
import com.purnendu.timetable.Repository
import com.purnendu.timetable.database.Database
import com.purnendu.timetable.databinding.FragmentCreateScreenBinding
import com.purnendu.timetable.ui.viewmodel.CreateScreenViewModel
import com.purnendu.timetable.ui.viewmodel.CreateScreenViewModelFactory


class CreateScreen : Fragment() {

    private var _binding: FragmentCreateScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CreateScreenViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val repository = Repository(Database.getDataBase(requireContext()))
        viewModel = ViewModelProvider(this, CreateScreenViewModelFactory(repository))[CreateScreenViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding =FragmentCreateScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedDates= mutableListOf<DAY>()
        binding.dayComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DayComposeView(){
                    selectedDates.clear()
                    selectedDates.addAll(it)
                }
            }
        }


        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        binding.create.setOnClickListener {
            val taskName= binding.taskName.text.toString()
            val taskDescription=binding.taskDescription.text.toString()

            if(taskName.isBlank())
            {
                Toast.makeText( context,"Task name requires", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(taskDescription.isBlank())
            {
                Toast.makeText( context,"Task description requires", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(selectedDates.isEmpty())
            {
                Toast.makeText( context,"Select date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            selectedDates.forEach {
                viewModel.insertTask(taskName,taskDescription,it)
            }

            findNavController().navigate(R.id.action_createScreen_to_homeScreen)
        }






    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}