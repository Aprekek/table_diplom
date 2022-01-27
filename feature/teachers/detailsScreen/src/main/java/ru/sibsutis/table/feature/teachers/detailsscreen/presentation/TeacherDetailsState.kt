package ru.sibsutis.table.feature.teachers.detailsscreen.presentation

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

sealed class TeacherDetailsState {

	object Initialize : TeacherDetailsState()

	object Loading : TeacherDetailsState()

	class Content(val teacher: Teacher) : TeacherDetailsState()
}