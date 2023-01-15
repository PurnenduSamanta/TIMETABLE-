package com.purnendu.timetable.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.purnendu.timetable.DAY

@Entity(tableName = "Task")
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long=0,
    val taskName: String,
    val taskDescription: String,
    val selectedDate:DAY
)