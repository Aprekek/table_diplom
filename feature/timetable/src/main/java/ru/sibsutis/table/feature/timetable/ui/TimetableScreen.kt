package ru.sibsutis.table.feature.timetable.ui

import androidx.compose.runtime.Composable
import ru.sibsutis.table.navigation.screens.timetable.TimeTableContent

object TimetableScreen : TimeTableContent {

	override fun route(group: String): @Composable () -> Unit {
		return { Content(group) }
	}

	@Composable
	private fun Content(group: String) {
	}
}