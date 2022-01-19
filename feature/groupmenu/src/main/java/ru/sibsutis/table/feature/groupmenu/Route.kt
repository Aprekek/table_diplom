package ru.sibsutis.table.feature.groupmenu

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.coroutines.FlowPreview
import ru.sibsutis.table.feature.groupmenu.ui.GroupMenuScreen

@FlowPreview
@ExperimentalMaterialApi
fun groupMenuRoute(navBuilder: NavGraphBuilder, navController: NavController) {
	navBuilder.composable(GroupMenuScreen.ROUTE) {
		GroupMenuScreen.Content(navController = navController)
	}
}