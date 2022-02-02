package ru.sibsutis.table.feature.timetable.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import ru.sibsutis.table.feature.timetable.R
import ru.sibsutis.table.feature.timetable.presentation.TimetableState
import ru.sibsutis.table.feature.timetable.presentation.TimetableViewModel
import ru.sibsutis.table.navigation.screens.timetable.TimeTableContent
import ru.sibsutis.table.shared.interconnectors.BottomNavTimetableInterconnector
import ru.sibsutis.table.shared.ui.ErrorScreen
import ru.sibsutis.table.shared.ui.LoadingScreen

object TimetableScreen : TimeTableContent {

	override fun route(group: String): @Composable () -> Unit {
		return { Content(group) }
	}

	@Composable
	private fun Content(group: String) {
		val context = LocalContext.current
		val viewModel by viewModel<TimetableViewModel> {
			parametersOf(group)
		}

		val state by viewModel.state.collectAsState()
		val week by viewModel.week.collectAsState()
		val day by viewModel.day.collectAsState()

		val weeksNames = remember {
			context.resources.getStringArray(R.array.weeks)
		}
		val dayNames = remember {
			context.resources.getStringArray(R.array.days)
		}

		val pagerState = rememberPagerState(initialPage = day)

		LaunchedEffect(pagerState.currentPage) {
			viewModel.onDaySelect(pagerState.currentPage)
		}

		LaunchedEffect(true) {
			BottomNavTimetableInterconnector.reselected.onEach {
				// If pager was initialized
				if (pagerState.pageCount > 0 && viewModel.reselectedValue != it) {
					viewModel.setCurrentDays()
					viewModel.reselectedValue = it
					launch {
						pagerState.animateScrollToPage(viewModel.currentDay)
					}
				}
			}.launchIn(this)
		}

		Scaffold(
			topBar = {
				TimetableAppBar(
					week = week,
					day = day,
					weeksNames = weeksNames,
					dayNames = dayNames,
					pagerState = pagerState,
					dates = viewModel.getDates(),
					onWeekSelected = { viewModel.onWeekSelect(it) },
					onDaySelected = { viewModel.onDaySelect(it) }
				)
			}
		) {
			when (state) {
				is TimetableState.Initial,
				is TimetableState.Loading -> LoadingScreen()

				is TimetableState.Error   -> ErrorScreen { }

				is TimetableState.Content -> TimetableMainContent(
					modifier = Modifier
						.fillMaxSize()
						.padding(bottom = it.calculateBottomPadding()),
					context = context,
					pagerState = pagerState,
					pagesCount = TimetableViewModel.TOTAL_DAYS,
					lessons = (state as TimetableState.Content).lessons
				)
			}
		}

		DisposableEffect(true) {
			onDispose {
				viewModel.onDaySelect(pagerState.currentPage)
			}
		}
	}
}