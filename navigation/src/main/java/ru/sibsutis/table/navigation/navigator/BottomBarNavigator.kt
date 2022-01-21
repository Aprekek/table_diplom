package ru.sibsutis.table.navigation.navigator

import kotlinx.coroutines.flow.MutableStateFlow
import java.io.Serializable

class BottomBarNavigator(
	currentScreen: String
) : Serializable {

	private val backStack: MutableList<String> = mutableListOf(currentScreen)

	var currentScreen = MutableStateFlow(currentScreen)

	fun navigateTo(screenId: String) {
		val prevPosition = backStack.indexOf(screenId)

		if (prevPosition == -1) {
			backStack.add(screenId)
			currentScreen.value = screenId
			return
		}

		if (screenId == backStack.last())
			return

		backStack.removeAt(prevPosition)
		backStack.add(screenId)
		currentScreen.value = screenId
	}

	fun popBack(): Boolean {
		return if (backStack.size > 1) {
			backStack.removeLast()
			currentScreen.value = backStack.last()
			true
		} else {
			false
		}
	}
}