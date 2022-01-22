package ru.sibsutis.table.features.teachers.listscreen.presentation

import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher

sealed class TeachersListState {

	object Initialize : TeachersListState()

	object Loading : TeachersListState()

	class Content(
		val teachersList: List<Teacher>
	) : TeachersListState()

	object Error : TeachersListState()
}