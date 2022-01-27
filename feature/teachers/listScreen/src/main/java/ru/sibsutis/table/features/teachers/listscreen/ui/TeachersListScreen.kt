package ru.sibsutis.table.features.teachers.listscreen.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.features.teachers.listscreen.presentation.SearchWidgetState
import ru.sibsutis.table.features.teachers.listscreen.presentation.TeachersListState
import ru.sibsutis.table.features.teachers.listscreen.presentation.TeachersListViewModel
import ru.sibsutis.table.features.teachers.listscreen.ui.components.MainAppBar
import ru.sibsutis.table.features.teachers.listscreen.ui.components.TeachersList
import ru.sibsutis.table.navigation.screens.teachers.TeacherRouter
import ru.sibsutis.table.navigation.screens.teachers.TeachersContent
import ru.sibsutis.table.shared.ui.EmptyScreen
import ru.sibsutis.table.shared.ui.ErrorScreen
import ru.sibsutis.table.shared.ui.LoadingScreen

@ExperimentalFoundationApi
object TeachersListScreen : TeachersContent {

	override fun route(navController: NavController): @Composable () -> Unit {
		return { Content(navController = navController) }
	}

	@Composable
	fun Content(navController: NavController) {

		val viewModel by viewModel<TeachersListViewModel>()

		val state by viewModel.state.collectAsState()

		val searchWidgetState by viewModel.searchWidgetState

		val searchTextState by viewModel.searchTextState

		LaunchedEffect(navController) {
			viewModel.setRouter(get(TeacherRouter::class.java) {
				parametersOf(navController)
			})
		}

		Scaffold(
			topBar = {
				MainAppBar(
					searchWidgetState = searchWidgetState,
					searchTextState = searchTextState,
					onTextChange = {
						viewModel.updateSearchTextState(newValue = it)
					},
					onCloseClicked = {
						viewModel.reset()
						viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
					},
					onSearchClicked = {
						viewModel.search(it)
					},
					onSearchTriggered = {
						viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
					}
				)
			}
		) {
			when (state) {
				is TeachersListState.Loading,
				TeachersListState.Initialize -> LoadingScreen()

				is TeachersListState.Error   -> ErrorScreen(reload = { viewModel.initialize() })

				is TeachersListState.Content -> ContentScreen(state as TeachersListState.Content) { viewModel.navigateToDetailsScreen(it.name) }
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