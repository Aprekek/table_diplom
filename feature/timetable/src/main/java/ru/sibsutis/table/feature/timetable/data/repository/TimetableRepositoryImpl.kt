package ru.sibsutis.table.feature.timetable.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sibsutis.table.feature.timetable.data.datasource.TimetableDataSource
import ru.sibsutis.table.feature.timetable.data.mappers.toDatabaseEntity
import ru.sibsutis.table.feature.timetable.data.mappers.toEntity
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson
import ru.sibsutis.table.feature.timetable.domain.repository.TimetableRepository
import ru.sibsutis.table.shared.lesson.domain.WeekType

class TimetableRepositoryImpl(
	private val dataSource: TimetableDataSource
) : TimetableRepository {

	override suspend fun updateTimetableStorageWithRemoteData(group: String) {
		withContext(Dispatchers.IO) {
			val lessons = dataSource.getLessonsForGroupRemote(group.lowercase()).toDatabaseEntity()
			dataSource.updateLocalLessonsStorage(lessons)
		}
	}

	override suspend fun getTimetable(week: WeekType): Map<Int, List<Lesson>> =
		withContext(Dispatchers.IO) {
			val timetable = dataSource.getTimetable(week).toEntity().groupBy { it.day.value }.toSortedMap()

			timetable.forEach { (key, value) ->
				launch {
					timetable[key] = value.sortedBy { it.startTime }
				}
			}

			timetable
		}
}