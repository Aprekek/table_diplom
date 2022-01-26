package ru.sibsutis.table.feature.timetable.domain.usecases

import ru.sibsutis.table.feature.timetable.domain.repository.TimetableRepository

class UpdateTimetableLocalStorageUseCase(
	private val repository: TimetableRepository
) {

	suspend operator fun invoke(group: String) {
		repository.updateTimetableStorageWithRemoteData(group)
	}
}