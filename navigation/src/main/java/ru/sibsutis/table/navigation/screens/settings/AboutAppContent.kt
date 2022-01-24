package ru.sibsutis.table.navigation.screens.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface AboutAppContent {

	companion object : BaseNavInfo {

		override val path = "settings/appInfo"

		fun createPath() = path
	}

	fun route(navGraphBuilder: NavGraphBuilder, navController: NavController)
}