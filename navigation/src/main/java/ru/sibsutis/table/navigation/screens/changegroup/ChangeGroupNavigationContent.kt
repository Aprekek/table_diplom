package ru.sibsutis.table.navigation.screens.changegroup

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface ChangeGroupNavigationContent {

	class Arguments {
		companion object {

			const val GROUP_NAME = "group_name"
		}
	}

	companion object : BaseNavInfo {

		override val path = "change_group/{${Arguments.GROUP_NAME}}"

		fun createPath(groupName: String) = "change_group/$groupName"
	}

	fun route(navGraphBuilder: NavGraphBuilder, navController: NavController)
}