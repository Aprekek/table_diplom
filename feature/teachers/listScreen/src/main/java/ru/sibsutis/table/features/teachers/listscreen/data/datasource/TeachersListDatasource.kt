package ru.sibsutis.table.features.teachers.listscreen.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.entities.TeacherEntity
import ru.sibsutis.table.features.teachers.listscreen.data.model.TeachersListModel

interface TeachersListDatasource {

	suspend fun getRemoteTeachersData(): List<TeachersListModel>

	fun getTeachersList(): Flow<List<TeacherEntity>>
}