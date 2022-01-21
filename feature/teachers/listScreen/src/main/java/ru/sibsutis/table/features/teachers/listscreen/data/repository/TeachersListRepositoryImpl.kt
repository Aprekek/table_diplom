package ru.sibsutis.table.features.teachers.listscreen.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.sibsutis.table.database.dao.TeachersListDao
import ru.sibsutis.table.features.teachers.listscreen.data.datasource.TeachersListDatasource
import ru.sibsutis.table.features.teachers.listscreen.data.mapper.toDatabaseEntity
import ru.sibsutis.table.features.teachers.listscreen.data.mapper.toEntityList
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.features.teachers.listscreen.domain.repository.TeachersListRepository

class TeachersListRepositoryImpl(
	private val datasource: TeachersListDatasource,
	private val dao: TeachersListDao
) : TeachersListRepository {

	override suspend fun updateLocalStorageWithRemoteData() {
		withContext(Dispatchers.IO) {
			val remoteData = datasource.getRemoteTeachersData().toDatabaseEntity()
			dao.replaceOldDataWithNewData(remoteData)
		}
	}

	override fun getTeachersList(): Flow<List<Teacher>> =
		dao.getTeachersList().toEntityList().flowOn(Dispatchers.IO)
}