package ru.sibsutis.table.navigation.screens.mainbottomnavigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface MainBottomNavigationContent {

	class Arguments {
		companion object {

			const val GROUP_NAME = "groupName"
		}
	}

	companion object : BaseNavInfo {

		override val path = "main_navigation/{${Arguments.GROUP_NAME}}"

		fun createPath(groupName: String): String {
			return "main_navigation/${groupName}"
		}
	}

	fun route(graphBuilder: NavGraphBuilder, navController: NavController, groupName: String)
}