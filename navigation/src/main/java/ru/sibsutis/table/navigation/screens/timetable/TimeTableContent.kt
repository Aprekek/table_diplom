package ru.sibsutis.table.navigation.screens.timetable

import androidx.compose.runtime.Composable
import ru.sibsutis.table.navigation.BaseNavInfo

interface TimeTableContent {

	companion object : BaseNavInfo {

		override val path = "time_table"
	}

	fun route(group: String): @Composable () -> Unit
}