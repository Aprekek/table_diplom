package ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

interface TeacherDetailsDataSource {

	suspend fun getTeacher(name: String): Teacher
}