package ru.sibsutis.table.shared.group.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.shared.group.domain.entities.Group

interface GroupMenuRepository {

	suspend fun updateLocalStorageWithRemoteData()

	fun getGroups(filter: String): Flow<List<Group>>

	suspend fun isGroupExist(filter: String): String?
}