package ru.sibsutis.table.feature.groupmenu.data.api

import retrofit2.http.GET
import ru.sibsutis.table.feature.groupmenu.data.model.GroupModel

interface GroupsMenuApi {

	@GET("groups_list")
	suspend fun get(): List<GroupModel>
}