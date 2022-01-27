package ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

interface TeacherDetailsRepository {

	suspend fun getTeacher(name: String): Teacher
}