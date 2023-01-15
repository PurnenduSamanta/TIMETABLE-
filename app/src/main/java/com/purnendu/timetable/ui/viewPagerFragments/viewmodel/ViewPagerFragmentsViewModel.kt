package com.purnendu.timetable.ui.viewPagerFragments.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purnendu.timetable.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewPagerFragmentsViewModel(private val repository: Repository) : ViewModel() {


    fun getAllTask() =repository.getAllTaskList()

    fun deleteTask(taskId:Long)=viewModelScope.launch(Dispatchers.IO) { repository.deleteTask(taskId) }



}