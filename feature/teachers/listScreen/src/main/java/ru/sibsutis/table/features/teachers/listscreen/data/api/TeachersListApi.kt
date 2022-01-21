package ru.sibsutis.table.features.teachers.listscreen.data.api

import retrofit2.http.GET
import ru.sibsutis.table.features.teachers.listscreen.data.model.TeachersListModel

interface TeachersListApi {

	@GET("/teachers_list")
	suspend fun getTeachersList(): List<TeachersListModel>
}