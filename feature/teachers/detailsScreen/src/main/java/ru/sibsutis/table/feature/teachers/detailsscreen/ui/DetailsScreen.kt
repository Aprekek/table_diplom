package ru.sibsutis.table.feature.teachers.detailsscreen.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.sibsutis.table.navigation.screens.teachers.TeachersDetailContent

object DetailsScreen : TeachersDetailContent {

	override fun route(navGraphBuilder: NavGraphBuilder, navController: NavController) {
		navGraphBuilder.composable(TeachersDetailContent.path) {
			Content(navController)
		}
	}

	@Composable
	fun Content(navController: NavController) {
		Text(text = "test")
	}
}