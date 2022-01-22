package ru.sibsutis.table.navigation.screens.settings

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.sibsutis.table.navigation.BaseNavInfo

interface SettingsContent {

	companion object : BaseNavInfo {

		override val path = "settings"
	}

	fun route(navController: NavController, group: String): @Composable () -> Unit
}