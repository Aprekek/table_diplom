package ru.sibsutis.table.navigation.screens.mainbottomnavigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface MainBottomNavigationContent {

	companion object : BaseNavInfo {

		override val path = "main_navigation/{groupName}"

		fun createPath(groupName: String): String {
			return "main_navigation/${groupName}"
		}
	}

	fun route(graphBuilder: NavGraphBuilder, navController: NavController)
}