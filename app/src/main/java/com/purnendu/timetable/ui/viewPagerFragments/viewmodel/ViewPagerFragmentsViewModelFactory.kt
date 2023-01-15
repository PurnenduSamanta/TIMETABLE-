package com.purnendu.timetable.ui.viewPagerFragments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.purnendu.timetable.Repository


class ViewPagerFragmentsViewModelFactory(private val repository : Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewPagerFragmentsViewModel(repository) as T
    }
}
