package com.purnendu.timetable

import androidx.lifecycle.LiveData
import com.purnendu.timetable.database.Database
import com.purnendu.timetable.database.TaskModel

class Repository(database: Database) {
    private val taskDao = database.TaskDao()
    private val _allTask: LiveData<List<TaskModel>>
        get() = taskDao.getAllTasks()

    suspend fun insertTask(taskName: String, taskDesc: String, selectedDate: DAY) {
        taskDao.insertTask(
            TaskModel(
                taskName = taskName,
                taskDescription = taskDesc,
                selectedDate = selectedDate
            )
        )
    }

    fun getAllTaskList():LiveData<List<TaskModel>> = _allTask


    suspend fun deleteTask(taskId:Long)= taskDao.deleteTask(taskId)



}