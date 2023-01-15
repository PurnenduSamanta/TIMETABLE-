package com.purnendu.timetable.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(data: TaskModel)


    @Query("SELECT * FROM `Task`")
    fun getAllTasks(): LiveData<List<TaskModel>>


    @Query("DELETE FROM `Task` where taskId=:taskId")
    suspend fun deleteTask(taskId:Long)


}