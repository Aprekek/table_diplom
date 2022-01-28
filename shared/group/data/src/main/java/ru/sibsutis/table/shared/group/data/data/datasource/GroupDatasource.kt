package ru.sibsutis.table.shared.group.data.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.entities.GroupEntity
import ru.sibsutis.table.shared.group.data.data.model.GroupModel

interface GroupDatasource {

	suspend fun getRemoteGroupsData(): List<GroupModel>

	fun getGroups(filter: String): Flow<List<GroupEntity>>

	suspend fun isGroupExists(filter: String): String?

	suspend fun insertGroups(groups: List<GroupEntity>)
}