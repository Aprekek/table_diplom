package ru.sibsutis.table.feature.teachers.detailsscreen.presentation

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

sealed class DetailsState {

	object Initialize : DetailsState()

	object Loading : DetailsState()

	class Content(
		val teacher: Teacher
	) : DetailsState()
}