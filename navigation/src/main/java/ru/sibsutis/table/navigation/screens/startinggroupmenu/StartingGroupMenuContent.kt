package ru.sibsutis.table.navigation.screens.startinggroupmenu

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface StartingGroupMenuContent : BaseNavInfo {

	fun route(navBuilder: NavGraphBuilder, navController: NavController)
}