package ru.sibsutis.table.feature.teachers.detailsscreen.domain.usecase

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository.TeacherDetailsRepository

class GetTeacherUseCase(
	private val repository: TeacherDetailsRepository
) {

	suspend operator fun invoke(name: String) = repository.getTeacher(name)
}