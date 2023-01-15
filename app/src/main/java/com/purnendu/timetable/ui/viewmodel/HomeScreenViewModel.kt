package com.purnendu.timetable.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.purnendu.timetable.DAY
import com.purnendu.timetable.Repository

class HomeScreenViewModel(private val repository: Repository) : ViewModel() {

   fun getAllTask()= repository.getAllTaskList()


    fun separateTaskByDay(dayList:List<DAY>)
    {

    }












}