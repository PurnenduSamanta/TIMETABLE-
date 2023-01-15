package com.purnendu.timetable.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.purnendu.timetable.DAY


class Converters {
    @TypeConverter
    fun fromSelectedDateJson(value: String?): DAY {
        val type = object : TypeToken<DAY>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toSelectedDateJson(day: DAY): String {
        val gson = Gson()
        return gson.toJson(day)
    }
}