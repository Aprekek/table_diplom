package ru.sibsutis.table.features.teachers.listscreen.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher

interface TeachersListRepository {

	suspend fun updateLocalStorageWithRemoteData()

	fun getTeachersList(searchText: String): Flow<List<Teacher>>
}