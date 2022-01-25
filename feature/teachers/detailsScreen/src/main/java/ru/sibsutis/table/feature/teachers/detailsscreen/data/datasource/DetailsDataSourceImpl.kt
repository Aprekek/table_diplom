package ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.database.dao.TeacherDetailsDao
import ru.sibsutis.table.feature.teachers.detailsscreen.data.mapper.toModel
import ru.sibsutis.table.feature.teachers.detailsscreen.data.model.TeacherModel

class DetailsDataSourceImpl(
	private val dao: TeacherDetailsDao
) : DetailsDataSource {

	override suspend fun getTeacher(name: String): TeacherModel =
		withContext(Dispatchers.IO) {
			dao.getTeacher(name).toModel()
		}
}