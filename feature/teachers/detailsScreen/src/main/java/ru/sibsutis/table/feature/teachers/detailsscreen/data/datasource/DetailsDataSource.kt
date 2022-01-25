package ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource

import ru.sibsutis.table.feature.teachers.detailsscreen.data.model.TeacherModel

interface DetailsDataSource {

	suspend fun getTeacher(name: String): TeacherModel
}