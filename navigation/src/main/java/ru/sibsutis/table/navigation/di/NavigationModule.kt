package ru.sibsutis.table.navigation.di

import androidx.navigation.NavController
import org.koin.dsl.module
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuRouter

val navigationModule = module {

	factory { (navController: NavController) -> StartingGroupMenuRouter(navController) }
}