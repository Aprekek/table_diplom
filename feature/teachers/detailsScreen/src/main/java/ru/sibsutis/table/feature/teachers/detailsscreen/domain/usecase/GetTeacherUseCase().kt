package ru.sibsutis.table.feature.teachers.detailsscreen.domain.usecase

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository.DetailsRepository

class GetTeacherUseCase(
	private val repository: DetailsRepository
) {

	suspend operator fun invoke(name: String) = repository.getTeacher(name)
}