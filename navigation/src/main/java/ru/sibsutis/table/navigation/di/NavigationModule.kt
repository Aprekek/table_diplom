package ru.sibsutis.table.navigation.di

import androidx.navigation.NavController
import org.koin.dsl.module
import ru.sibsutis.table.navigation.screens.changegroup.ChangeGroupNavigationRouter
import ru.sibsutis.table.navigation.screens.settings.SettingsRouter
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuRouter
import ru.sibsutis.table.navigation.screens.teachers.TeacherRouter

val navigationModule = module {

	factory { (navController: NavController) -> StartingGroupMenuRouter(navController) }

	factory { (navController: NavController) -> SettingsRouter(navController) }

	factory { (navController: NavController) -> ChangeGroupNavigationRouter(navController) }

	factory { (navController: NavController) -> TeacherRouter(navController) }
}