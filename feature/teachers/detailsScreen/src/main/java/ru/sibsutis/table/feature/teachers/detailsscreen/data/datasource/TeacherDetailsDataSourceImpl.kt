package ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.database.dao.TeacherDetailsDao
import ru.sibsutis.table.feature.teachers.detailsscreen.data.mapper.toEntity
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

class TeacherDetailsDataSourceImpl(
	private val dao: TeacherDetailsDao
) : TeacherDetailsDataSource {

	override suspend fun getTeacher(name: String): Teacher =
		withContext(Dispatchers.IO) {
			dao.getTeacher(name).toEntity()
		}
}