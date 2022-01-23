package ru.sibsutis.table.features.teachers.listscreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.viewModel
import ru.sibsutis.table.features.teachers.listscreen.R
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.features.teachers.listscreen.presentation.TeachersListState
import ru.sibsutis.table.features.teachers.listscreen.presentation.TeachersListViewModel
import ru.sibsutis.table.features.teachers.listscreen.ui.components.TeachersList
import ru.sibsutis.table.navigation.screens.teachers.TeachersContent
import ru.sibsutis.table.shared.ui.EmptyScreen
import ru.sibsutis.table.shared.ui.ErrorScreen
import ru.sibsutis.table.shared.ui.LoadingScreen
import ru.sibsutis.table.shared.ui.ToolbarDT

@ExperimentalFoundationApi
object TeachersListScreen : TeachersContent {

	override fun route(navController: NavController): @Composable () -> Unit {
		return { Content(navController = navController) }
	}

	@Composable
	fun Content(navController: NavController) {

		val context = LocalContext.current

		val viewModel by viewModel<TeachersListViewModel>()

		val state by viewModel.state.collectAsState()

		Scaffold(
			topBar = {
				ToolbarDT(
					title = stringResource(id = R.string.teacher_title),
					enableBackButton = false
				)
			}
		) {
			when (state) {
				is TeachersListState.Loading,
				TeachersListState.Initialize -> LoadingScreen()

				is TeachersListState.Error   -> ErrorScreen(reload = { viewModel.initialize() })

				is TeachersListState.Content -> ContentScreen(state as TeachersListState.Content) { viewModel.navigateToSelectedTeacher(it) }
			}
		}
	}

	@Composable
	private fun ContentScreen(state: TeachersListState.Content, onClick: (Teacher) -> Unit) {
		if (state.teachersList.isEmpty()) {
			EmptyScreen()
		} else {
			TeachersList(teachers = state.teachersList, onTeacherClick = onClick)
		}
	}
}