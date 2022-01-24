package ru.sibsutis.table.navigation.screens.teachers

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.sibsutis.table.navigation.BaseNavInfo

interface TeachersContent {

	companion object : BaseNavInfo {

		override val path = "teachers"
	}

	fun route(navController: NavController): @Composable () -> Unit
}