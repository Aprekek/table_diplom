package ru.sibsutis.table.feature.teachers.detailsscreen.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.sibsutis.table.navigation.screens.teachers.TeachersDetailContent

object DetailsScreen : TeachersDetailContent {

	override fun route(navGraphBuilder: NavGraphBuilder, navController: NavController, teacherName: String) {
		navGraphBuilder.composable(TeachersDetailContent.path) {
			val teacher = it.arguments?.getString(TeachersDetailContent.Arguments.TEACHER_NAME) ?: teacherName
			Content(navController, teacher)
		}
	}

	@Composable
	fun Content(navController: NavController, teacherName: String) {
		Text(text = teacherName)
	}
}