package ru.sibsutis.table.settings

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.sibsutis.table.navigation.screens.settings.SettingsContent
import ru.sibsutis.table.navigation.screens.settings.SettingsRouter
import ru.sibsutis.table.shared.ui.ToolbarDT

object SettingsScreen : SettingsContent {

	private data class ThemeData(
		val title: String,
		@DrawableRes val icon: Int
	)

	override fun route(navController: NavController, group: String): @Composable () -> Unit {
		return { Content(navController, group) }
	}

	@Composable
	private fun Content(navController: NavController, group: String) {

		val router = remember(navController) {
			get<SettingsRouter>(SettingsRouter::class.java) {
				parametersOf(navController)
			}
		}

		var showThemeDialog by remember { mutableStateOf(false) }

		if (showThemeDialog) {
			Dialog(onDismissRequest = { showThemeDialog = false }) {
				ThemeSettingsList(stringResource(id = R.string.light_theme)) {
					showThemeDialog = false
					// TODO change theme mode
				}
			}
		}

		Scaffold(
			topBar = {
				ToolbarDT(
					title = group,
					enableBackButton = false
				)
			},
		) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(vertical = 10.dp)
					.verticalScroll(ScrollState(0)),
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.align(Alignment.TopCenter)
				) {
					SettingsElement(
						text = stringResource(id = R.string.theme),
						image = R.drawable.ic_light_mode // TODO icon depend on theme
					) { showThemeDialog = true }

					Spacer(modifier = Modifier.height(10.dp))

					SettingsElement(
						text = stringResource(id = R.string.change_group),
						image = R.drawable.ic_change_group
					) { router.navigateToGroupMenuScreen(group) }
				}

				SettingsElement(
					modifier = Modifier.align(Alignment.BottomCenter),
					text = stringResource(id = R.string.about_app),
					image = R.drawable.ic_info
				) { router.navigateToAboutAppScreen() }
			}
		}
	}

	@Composable
	private fun SettingsElement(
		modifier: Modifier = Modifier,
		text: String,
		@DrawableRes image: Int? = null,
		onClick: () -> Unit
	) {
		val elevation = 1.dp

		Column(modifier = modifier) {
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.clickable(
						interactionSource = MutableInteractionSource(),
						indication = rememberRipple(),
						onClick = onClick
					),
				elevation = elevation,
				shape = RectangleShape,
			) {
				Box(
					modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 20.dp, horizontal = 15.dp)
				) {
					Text(
						modifier = Modifier.align(Alignment.CenterStart),
						text = text
					)

					if (image != null) {
						Icon(
							modifier = Modifier.align(Alignment.CenterEnd),
							painter = painterResource(id = image),
							contentDescription = null
						)
					}
				}
			}

			Spacer(modifier = Modifier.height(elevation))
		}
	}

	@Composable
	private fun ThemeSettingsList(
		currentTheme: String,
		onThemeSelectConfirmation: (theme: String) -> Unit
	) {
		val context = LocalContext.current
		var selectedTheme by remember { mutableStateOf(currentTheme) }
		val themeItems = remember {
			listOf(
				ThemeData(
					title = context.getString(R.string.light_theme),
					icon = R.drawable.ic_light_mode
				),
				ThemeData(
					title = context.getString(R.string.dark_theme),
					icon = R.drawable.ic_dark_mode
				),
				ThemeData(
					title = context.getString(R.string.system_theme),
					icon = R.drawable.ic_system_mode
				)
			)
		}

		Surface(shape = RoundedCornerShape(10.dp)) {
			Column(modifier = Modifier.padding(vertical = 10.dp)) {
				themeItems.forEach { item ->
					ThemeSettingsListItem(
						title = item.title,
						icon = item.icon,
						selected = selectedTheme == item.title
					) { selectedTheme = item.title }
				}

				Text(
					text = stringResource(R.string.confirm),
					color = Color.Blue,
					modifier = Modifier
						.align(Alignment.End)
						.padding(top = 10.dp, end = 15.dp)
						.clickable { onThemeSelectConfirmation(selectedTheme) }
				)
			}
		}
	}

	@Composable
	private fun ThemeSettingsListItem(
		title: String,
		@DrawableRes icon: Int,
		selected: Boolean,
		onSelect: (element: String) -> Unit
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.selectable(selected = selected) { onSelect(title) }
				.padding(horizontal = 15.dp),
		) {
			RadioButton(
				selected = selected,
				onClick = { onSelect(title) },
				modifier = Modifier.align(Alignment.CenterVertically)
			)

			Box(
				modifier = Modifier
					.fillMaxWidth()
					.align(Alignment.CenterVertically)
			) {
				Text(
					text = title,
					modifier = Modifier.align(Alignment.CenterStart)
				)
				Icon(
					painter = painterResource(icon),
					contentDescription = null,
					modifier = Modifier.align(Alignment.CenterEnd)
				)
			}
		}
	}
}