package ru.sibsutis.table.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.FlowPreview
import ru.sibsutis.table.feature.groupmenu.groupMenuRoute

@FlowPreview
@ExperimentalMaterialApi
@Composable
fun GlobalController(startDestination: String) {
	val navController = rememberNavController()

	NavHost(
		navController = navController,
		startDestination = startDestination
	) {
		groupMenuRoute(this, navController)
	}
}