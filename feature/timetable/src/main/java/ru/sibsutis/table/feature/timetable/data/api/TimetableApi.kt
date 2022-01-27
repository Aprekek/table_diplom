package ru.sibsutis.table.feature.timetable.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.sibsutis.table.feature.timetable.data.model.LessonModel

interface TimetableApi {

	@GET("lessons/{group}")
	suspend fun getLessonsForGroup(
		@Path("group") group: String
	): List<LessonModel>
}