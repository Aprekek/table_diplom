package ru.sibsutis.table.feature.groupmenu.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.sibsutis.table.database.dao.GroupDao
import ru.sibsutis.table.feature.groupmenu.data.datasource.GroupDatasource
import ru.sibsutis.table.feature.groupmenu.data.mapper.toDatabaseEntity
import ru.sibsutis.table.feature.groupmenu.data.mapper.toEntityList
import ru.sibsutis.table.feature.groupmenu.domain.repository.GroupMenuRepository

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

	override suspend fun getGroups() = dao.getGroupsList().toEntityList().flowOn(Dispatchers.IO)
}