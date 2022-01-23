package ru.sibsutis.table.feature.groups.startingscreen.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.dao.GroupDao
import ru.sibsutis.table.database.entities.GroupEntity
import ru.sibsutis.table.feature.groups.startingscreen.data.api.GroupsMenuApi
import ru.sibsutis.table.feature.groups.startingscreen.data.model.GroupModel

class GroupDatasourceImpl(
	private val dao: GroupDao,
	private val api: GroupsMenuApi
) : GroupDatasource {

	override suspend fun getRemoteGroupsData(): List<GroupModel> = api.get()

	override fun getGroups(filter: String): Flow<List<GroupEntity>> = dao.getGroupsList(filter)

	override suspend fun isGroupExists(filter: String) = dao.isGroupExist(filter)
}