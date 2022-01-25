package ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository

import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

interface DetailsRepository {

	suspend fun getTeacher(name: String): Teacher
}