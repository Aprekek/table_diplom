package ru.sibsutis.table.features.mainbottomnavigationscreen.ui

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import ru.sibsutis.table.feature.timetable.ui.TimetableScreen
import ru.sibsutis.table.features.mainbottomnavigationscreen.R
import ru.sibsutis.table.features.teachers.listscreen.ui.TeachersListScreen
import ru.sibsutis.table.navigation.navigator.BottomBarNavigator
import ru.sibsutis.table.navigation.screens.mainbottomnavigation.MainBottomNavigationContent
import ru.sibsutis.table.navigation.screens.settings.SettingsContent
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuContent
import ru.sibsutis.table.navigation.screens.teachers.TeachersContent
import ru.sibsutis.table.navigation.screens.timetable.TimeTableContent
import ru.sibsutis.table.settings.SettingsScreen
import ru.sibsutis.table.shared.interconnectors.BottomNavTimetableInterconnector
import ru.sibsutis.table.shared.ui.BottomNavigationDt
import ru.sibsutis.table.shared.ui.domain.BottomNavigationItemEntity

object MainBottomNavigationScreen : MainBottomNavigationContent {

	override fun route(graphBuilder: NavGraphBuilder, navController: NavController, groupName: String) {
		graphBuilder.composable(MainBottomNavigationContent.path) { entry ->
			val group = entry.arguments?.getString(MainBottomNavigationContent.Arguments.GROUP_NAME) ?: groupName
			Content(navController, group)
		}
	}

	@Composable
	private fun Content(navController: NavController, group: String) {
		val context = LocalContext.current
		val coroutineScope = rememberCoroutineScope()
		val bottomBarNavigator = rememberSaveable { BottomBarNavigator(TimeTableContent.path) }
		val currentScreen by bottomBarNavigator.currentScreen.collectAsState()

		val bottomNavigationItemEntities = rememberSaveable {
			listOf(
				BottomNavigationItemEntity(
					image = R.drawable.ic_time_table,
					title = context.getString(R.string.time_table),
					route = TimeTableContent.path
				),
				BottomNavigationItemEntity(
					image = R.drawable.ic_teachers,
					title = context.getString(R.string.teacher),
					route = TeachersContent.path
				),
				BottomNavigationItemEntity(
					image = R.drawable.ic_settings,
					title = context.getString(R.string.settings),
					route = SettingsContent.path
				)
			)
		}

		Scaffold(
			bottomBar = {
				BottomNavigationDt(
					selectedItem = currentScreen,
					items = bottomNavigationItemEntities,
					onItemClick = {
						bottomBarNavigator.navigateTo(it.route)
						coroutineScope.launch {
							BottomNavTimetableInterconnector.onReselect()
						}
					}
				)
			}
		) {
			Box(
				modifier = Modifier
					.fillMaxSize()
					.padding(bottom = it.calculateBottomPadding())
			) {

				when (currentScreen) {
					TimeTableContent.path -> {
						TimetableScreen.route(group)()
					}

					TeachersContent.path  -> {
						TeachersListScreen.route(navController)()
					}

					SettingsContent.path  -> {
						SettingsScreen.route(navController, group)()
					}
				}
			}
		}

		BackHandler(enabled = true) {
			if (!bottomBarNavigator.popBack()) {
				val prevScreen = navController.currentBackStackEntry?.destination?.parent?.route
				if (prevScreen == null || prevScreen == StartingGroupMenuContent.path) {
					(context as Activity).finish()
				} else {
					navController.popBackStack()
				}
			}
		}
	}
}