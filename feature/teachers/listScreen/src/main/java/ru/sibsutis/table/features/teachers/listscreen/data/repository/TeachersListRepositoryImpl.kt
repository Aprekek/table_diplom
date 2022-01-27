package ru.sibsutis.table.features.teachers.listscreen.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.sibsutis.table.features.teachers.listscreen.data.datasource.TeachersListDatasource
import ru.sibsutis.table.features.teachers.listscreen.data.mapper.toDatabaseEntity
import ru.sibsutis.table.features.teachers.listscreen.data.mapper.toEntityList
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.features.teachers.listscreen.domain.repository.TeachersListRepository

class TeachersListRepositoryImpl(
	private val datasource: TeachersListDatasource,
) : TeachersListRepository {

	override suspend fun updateLocalStorageWithRemoteData() {
		withContext(Dispatchers.IO) {
			val remoteData = datasource.getRemoteTeachersData().toDatabaseEntity()
			datasource.replaceOldDataWithNewData(remoteData)
		}
	}

	override fun getTeachersList(searchText: String): Flow<List<Teacher>> =
		datasource.getTeachersList(searchText).toEntityList().flowOn(Dispatchers.IO)
}