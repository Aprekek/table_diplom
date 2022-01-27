package ru.sibsutis.table.feature.teachers.detailsscreen.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource.TeacherDetailsDataSource
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository.TeacherDetailsRepository

class TeacherDetailsRepositoryImpl(
	private val dataSource: TeacherDetailsDataSource
) : TeacherDetailsRepository {

	override suspend fun getTeacher(name: String): Teacher =
		withContext(Dispatchers.IO) {
			dataSource.getTeacher(name)
		}
}