package ru.sibsutis.table.feature.timetable.data.model

import com.squareup.moshi.Json

data class LessonModel(
	val name: String,
	val day: String,
	@Json(name = "start_time")
	val startTime: String,
	@Json(name = "end_time")
	val endTime: String,
	val teacher: String,
	val address: String,
	val type: Int,
	val week: Int
)