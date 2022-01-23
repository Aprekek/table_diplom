package ru.sibsutis.table.feature.groups.startingscreen.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.sibsutis.table.database.dao.GroupDao
import ru.sibsutis.table.feature.groups.startingscreen.data.datasource.GroupDatasource
import ru.sibsutis.table.feature.groups.startingscreen.data.mapper.toDatabaseEntity
import ru.sibsutis.table.feature.groups.startingscreen.data.mapper.toEntityList
import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository

class GroupMenuRepositoryImpl(
	private val datasource: GroupDatasource,
	private val dao: GroupDao
) : GroupMenuRepository {

	override suspend fun updateLocalStorageWithRemoteData() {
		withContext(Dispatchers.IO) {
			val remoteData = datasource.getRemoteGroupsData().toDatabaseEntity()
			dao.insertGroups(remoteData)
		}
	}

	override fun getGroups(filter: String) = dao.getGroupsList(filter).toEntityList().flowOn(Dispatchers.IO)

	override suspend fun isGroupExist(filter: String): String? {
		return withContext(Dispatchers.IO) {
			dao.isGroupExist(filter)
		}
	}
}