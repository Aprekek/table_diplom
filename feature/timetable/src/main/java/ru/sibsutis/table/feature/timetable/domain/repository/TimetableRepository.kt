package ru.sibsutis.table.feature.timetable.domain.repository

import ru.sibsutis.table.feature.timetable.domain.entities.Lesson
import ru.sibsutis.table.shared.lesson.domain.WeekType

interface TimetableRepository {

	suspend fun updateTimetableStorageWithRemoteData(group: String)

	suspend fun getTimetable(week: WeekType): Map<Int, List<Lesson>>
}