package ru.sibsutis.table.feature.timetable.domain.usecases

import ru.sibsutis.table.feature.timetable.domain.repository.TimetableRepository
import ru.sibsutis.table.shared.lesson.domain.entities.WeekType

class GetTimetableUseCase(
	private val repository: TimetableRepository
) {

	suspend operator fun invoke(week: WeekType) = repository.getTimetable(week)
}