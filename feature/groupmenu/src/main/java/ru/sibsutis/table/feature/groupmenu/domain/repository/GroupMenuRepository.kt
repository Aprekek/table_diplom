package ru.sibsutis.table.feature.groupmenu.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.feature.groupmenu.domain.entities.Group

interface GroupMenuRepository {

	suspend fun updateLocalStorageWithRemoteData()

	suspend fun getGroups(): Flow<List<Group>>
}