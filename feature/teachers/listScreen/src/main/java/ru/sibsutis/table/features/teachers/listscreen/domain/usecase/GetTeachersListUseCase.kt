package ru.sibsutis.table.features.teachers.listscreen.domain.usecase

import ru.sibsutis.table.features.teachers.listscreen.domain.repository.TeachersListRepository

class GetTeachersListUseCase(
	private val repository: TeachersListRepository
) {

	operator fun invoke(searchText: String = "") = repository.getTeachersList(searchText)
}