package ru.sibsutis.table.navigation.screens.teachers

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface TeachersDetailContent {

	companion object : BaseNavInfo {

		override val path = "teachers/details"

		fun createPath() = path
	}

	fun route(navGraphBuilder: NavGraphBuilder, navController: NavController)
}