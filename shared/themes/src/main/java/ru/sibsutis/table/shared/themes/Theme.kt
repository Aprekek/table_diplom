package ru.sibsutis.table.shared.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DiplomTableTheme(
	isDarkMode: Boolean = isSystemInDarkTheme(),
	content: @Composable () -> Unit
) {
	val systemUiController = rememberSystemUiController()

	//TODO check user preferences for the theme mode
	DiplomThemeMode.isDarkMode = isDarkMode

	val colors = if (isDarkMode) {
		systemUiController.setStatusBarColor(color = grey4Dp)
		darkColorTheme
	} else {
		systemUiController.setStatusBarColor(color = lightColorTheme.primary)
		lightColorTheme
	}

	MaterialTheme(colors = colors) {
		content()
	}
}

object DiplomThemeMode {

	var isDarkMode by mutableStateOf(false)
	var isSystemMode by mutableStateOf(false)

	@Composable
	fun isDarkTheme() = if (isSystemMode) isSystemInDarkTheme() else isDarkMode
}