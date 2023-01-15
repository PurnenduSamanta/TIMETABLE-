package com.purnendu.timetable.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.purnendu.timetable.DAY


val daysMap = mapOf(
    0 to DAY.SUNDAY,
    1 to DAY.MONDAY,
    2 to DAY.TUESDAY,
    3 to DAY.WEDNESDAY,
    4 to DAY.THURSDAY,
    5 to DAY.FRIDAY,
    6 to DAY.SATURDAY
)

val daysInShort = arrayOf("S", "M", "T", "W", "T", "F", "S")


@Composable
fun DayComposeView(onSelectedDays: (List<DAY>) -> Unit) {


    val days =
        remember{mutableStateOf(
            (0..6).map {
                Day(
                    day = daysInShort[it],
                )
            }
        )}


    Row(
        modifier = Modifier
            .drawBehind {
                drawLine(
                    color = Color(0xFF4d0034),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 2.dp.toPx()
                )
                drawLine(
                    color = Color(0xFF4d0034),
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2.dp.toPx()
                )
                drawLine(
                    color = Color(0xFF4d0034),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2.dp.toPx()
                )
                drawLine(
                    color = Color(0xFF4d0034),
                    start = Offset(size.width, 0f),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        for (i in 0..6) {
            SingleDayComposeView(text = daysInShort[i], isSelected = days.value[i].isSelected) {
                days.value = days.value.mapIndexed { j, item ->
                    if (i == j) {
                        item.copy(isSelected = !item.isSelected)
                    } else item
                }
                onDaysSelect(daysList = days.value, onSelectedDays)
            }
        }
    }

}


@Composable
fun SingleDayComposeView(text: String, isSelected: Boolean, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .width(50.dp)
            .height(40.dp)
            .background(color = animateColorAsState(targetValue = if (isSelected) Color(0xFF4d0034) else Color.Transparent).value)
            .drawBehind {
                drawLine(
                    color = Color(0xFF4d0034),
                    start = Offset(size.width, 0f),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = animateColorAsState(targetValue = if (isSelected) Color.White else Color(0xFF4d0034)).value,
            fontWeight = FontWeight.Bold
        )

    }

}

private fun onDaysSelect(daysList: List<Day>, onSelectedDays: (List<DAY>) -> Unit) {
    val tempDaysList = mutableListOf<DAY>()
    daysList.forEachIndexed { index, day ->
        if (day.isSelected)
            tempDaysList.add(daysMap[index]!!)
    }
    onSelectedDays(tempDaysList)

}

data class Day(val day: String, val isSelected: Boolean = false)


