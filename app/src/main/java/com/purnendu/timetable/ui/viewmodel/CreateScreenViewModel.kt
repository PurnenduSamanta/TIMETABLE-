package com.purnendu.timetable.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.purnendu.timetable.DAY
import com.purnendu.timetable.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateScreenViewModel(private val repository: Repository) : ViewModel() {

    fun insertTask(taskName: String, taskDesc: String, selectedDate: DAY) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTask(
                taskName = taskName,
                taskDesc = taskDesc,
                selectedDate = selectedDate
            )
        }

    }

}