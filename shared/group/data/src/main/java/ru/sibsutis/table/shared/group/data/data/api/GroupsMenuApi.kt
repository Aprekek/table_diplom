package ru.sibsutis.table.shared.group.data.data.api

import retrofit2.http.GET
import ru.sibsutis.table.shared.group.data.data.model.GroupModel

interface GroupsMenuApi {

	@GET("/groups_list")
	suspend fun get(): List<GroupModel>
}