package ru.sibsutis.table.shared.interconnectors

import kotlinx.coroutines.flow.MutableStateFlow

object BottomNavTimetableInterconnector {

	private var value = 0
	val reselected = MutableStateFlow(value)

	suspend fun onReselect() {
		reselected.emit(++value)
	}
}