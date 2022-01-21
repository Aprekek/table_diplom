package ru.sibsutis.table.navigation.screens.startinggroupmenu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface StartingGroupMenuContent {

	companion object : BaseNavInfo {

		override val path = "group_menu"

		fun createPath() = path
	}

	fun route(navBuilder: NavGraphBuilder, navController: NavController)
}