package ru.sibsutis.table.feature.teachers.detailsscreen.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.TeacherDetailsState
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.TeacherDetailsViewModel
import ru.sibsutis.table.navigation.screens.teachers.TeachersDetailContent
import ru.sibsutis.table.shared.ui.ErrorScreen
import ru.sibsutis.table.shared.ui.LoadingScreen
import ru.sibsutis.table.shared.ui.ToolbarDT

object TeacherDetailsScreen : TeachersDetailContent {

	override fun route(navGraphBuilder: NavGraphBuilder, navController: NavController) {
		navGraphBuilder.composable(TeachersDetailContent.path) {
			val teacher = it.arguments?.getString(TeachersDetailContent.Arguments.TEACHER_NAME)
			Content(navController, teacher)
		}
	}

	@Composable
	fun Content(navController: NavController, teacherName: String?) {

		val viewModel by viewModel<TeacherDetailsViewModel> {
			parametersOf(teacherName ?: "")
		}

		val state by viewModel.state.collectAsState()

		Scaffold(
			topBar = {
				ToolbarDT(
					title = teacherName ?: "",
					onBackButtonClick = { navController.popBackStack() }
				)
			}) {

			if (teacherName.isNullOrEmpty()) {
				ErrorScreen { navController.popBackStack() }
			} else {
				when (state) {
					is TeacherDetailsState.Loading,
					TeacherDetailsState.Initialize -> LoadingScreen()

					is TeacherDetailsState.Content -> MainContent(state as TeacherDetailsState.Content)
				}
			}
		}
	}
}