package ru.sibsutis.table.feature.groups.startingscreen.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.entities.GroupEntity
import ru.sibsutis.table.feature.groups.startingscreen.data.model.GroupModel

interface GroupDatasource {

	suspend fun getRemoteGroupsData(): List<GroupModel>

	fun getGroups(filter: String): Flow<List<GroupEntity>>

	suspend fun isGroupExists(filter: String): String?
}