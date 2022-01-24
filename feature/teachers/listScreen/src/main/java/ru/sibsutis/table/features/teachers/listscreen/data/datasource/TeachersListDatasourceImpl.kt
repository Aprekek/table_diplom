package ru.sibsutis.table.features.teachers.listscreen.data.datasource

import kotlinx.coroutines.flow.Flow
import ru.sibsutis.table.database.dao.TeachersListDao
import ru.sibsutis.table.database.entities.TeacherEntity
import ru.sibsutis.table.features.teachers.listscreen.data.api.TeachersListApi
import ru.sibsutis.table.features.teachers.listscreen.data.model.TeachersListModel

class TeachersListDatasourceImpl(
	private val dao: TeachersListDao,
	private val api: TeachersListApi
) : TeachersListDatasource {

	override suspend fun getRemoteTeachersData(): List<TeachersListModel> = api.getTeachersList()

	override fun getTeachersList(searchText: String): Flow<List<TeacherEntity>> = dao.getTeachersList(searchText)
}