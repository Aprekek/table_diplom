package ru.sibsutis.table.feature.teachers.detailsscreen.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource.DetailsDataSource
import ru.sibsutis.table.feature.teachers.detailsscreen.data.mapper.toEntity
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository.DetailsRepository

class DetailsRepositoryImpl(
	private val dataSource: DetailsDataSource
) : DetailsRepository {

	override suspend fun getTeacher(name: String): Teacher =
		withContext(Dispatchers.IO) {
			dataSource.getTeacher(name).toEntity()
		}
}