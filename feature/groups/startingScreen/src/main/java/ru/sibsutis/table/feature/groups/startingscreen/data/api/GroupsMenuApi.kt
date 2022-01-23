package ru.sibsutis.table.feature.groups.startingscreen.data.api

import retrofit2.http.GET
import ru.sibsutis.table.feature.groups.startingscreen.data.model.GroupModel

interface GroupsMenuApi {

	@GET("/groups_list")
	suspend fun get(): List<GroupModel>
}