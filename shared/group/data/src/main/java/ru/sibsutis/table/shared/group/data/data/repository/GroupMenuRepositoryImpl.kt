package ru.sibsutis.table.shared.group.data.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.sibsutis.table.shared.group.data.data.datasource.GroupDatasource
import ru.sibsutis.table.shared.group.data.data.mapper.toDatabaseEntity
import ru.sibsutis.table.shared.group.data.data.mapper.toEntityList
import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository

class GroupMenuRepositoryImpl(
	private val datasource: GroupDatasource
) : GroupMenuRepository {

	override suspend fun updateLocalStorageWithRemoteData() {
		withContext(Dispatchers.IO) {
			val remoteData = datasource.getRemoteGroupsData().toDatabaseEntity()
			datasource.insertGroups(remoteData)
		}
	}

	override fun getGroups(filter: String) = datasource.getGroups(filter).toEntityList().flowOn(Dispatchers.IO)

	override suspend fun isGroupExist(filter: String): String? {
		return withContext(Dispatchers.IO) {
			datasource.isGroupExists(filter)
		}
	}
}