package com.purnendu.timetable.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purnendu.timetable.Repository


class CreateScreenViewModelFactory(private val repository : Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateScreenViewModel(repository) as T
    }
}