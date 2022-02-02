package ru.sibsutis.table.shared.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.java.KoinJavaComponent.get
import ru.sibsutis.table.preferences.preferences.ThemeModePreferences

enum class ThemeModes(val value: Int) {
	LIGHT(0),
	DARK(1),
	SYSTEM(2)
}

@Composable
fun DiplomTableTheme(
	isDarkMode: Boolean,
	content: @Composable () -> Unit
) {
	val systemUiController = rememberSystemUiController()

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

	private var isDarkMode by mutableStateOf(false)
	private var isSystemMode by mutableStateOf(false)

	var mode: ThemeModes = ThemeModes.SYSTEM
		set(newMode) {
			if (newMode == ThemeModes.SYSTEM) {
				isSystemMode = true
			} else {
				isDarkMode = newMode == ThemeModes.DARK
				isSystemMode = false
			}
			field = newMode
			get<ThemeModePreferences>(ThemeModePreferences::class.java).saveTheme(newMode.value)
		}

	@Composable
	fun isDarkTheme() = if (isSystemMode) isSystemInDarkTheme() else isDarkMode
}