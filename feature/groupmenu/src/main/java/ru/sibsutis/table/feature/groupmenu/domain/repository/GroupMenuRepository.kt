package ru.sibsutis.table.feature.groupmenu.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.feature.groupmenu.domain.entities.Group

interface GroupMenuRepository {

	suspend fun updateLocalStorageWithRemoteData()

	fun getGroups(filter: String): Flow<List<Group>>

	suspend fun isGroupExist(filter: String): String?
}