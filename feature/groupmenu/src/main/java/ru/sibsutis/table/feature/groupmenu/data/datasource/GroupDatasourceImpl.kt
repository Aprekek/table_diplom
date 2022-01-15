package ru.sibsutis.table.feature.groupmenu.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.dao.GroupDao
import ru.sibsutis.table.database.entities.GroupEntity
import ru.sibsutis.table.feature.groupmenu.data.api.GroupsMenuApi
import ru.sibsutis.table.feature.groupmenu.data.model.GroupModel

class GroupDatasourceImpl(
	private val dao: GroupDao,
	private val api: GroupsMenuApi
) : GroupDatasource {

	override suspend fun getRemoteGroupsData(): List<GroupModel> = api.get()

	override suspend fun getGroups(): Flow<List<GroupEntity>> = dao.getGroupsList()
}