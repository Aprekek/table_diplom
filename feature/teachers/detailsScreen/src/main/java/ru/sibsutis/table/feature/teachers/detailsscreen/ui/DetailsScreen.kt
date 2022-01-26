package ru.sibsutis.table.feature.teachers.detailsscreen.ui

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.DetailsState
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.DetailsViewModel
import ru.sibsutis.table.navigation.screens.teachers.TeachersDetailContent
import ru.sibsutis.table.shared.ui.LoadingScreen
import ru.sibsutis.table.shared.ui.ToolbarDT

object DetailsScreen : TeachersDetailContent {

	override fun route(navGraphBuilder: NavGraphBuilder, navController: NavController, teacherName: String) {
		navGraphBuilder.composable(TeachersDetailContent.path) {
			val teacher = it.arguments?.getString(TeachersDetailContent.Arguments.TEACHER_NAME) ?: teacherName
			Content(navController, teacher)
		}
	}

	@Composable
	fun Content(navController: NavController, teacherName: String) {

		val viewModel by viewModel<DetailsViewModel> {
			parametersOf(teacherName)
		}

		val state by viewModel.state.collectAsState()

		Scaffold(
			topBar = {
				ToolbarDT(
					title = teacherName,
					onBackButtonClick = { navController.popBackStack() }
				)
			}) {
			when (state) {
				is DetailsState.Loading,
				DetailsState.Initialize -> LoadingScreen()

				is DetailsState.Content -> MainContent(state as DetailsState.Content)
			}
		}
	}
}