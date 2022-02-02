package ru.sibsutis.table.feature.timetable.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import ru.sibsutis.table.feature.timetable.presentation.TimetableViewModel

@Composable
internal fun TimetableAppBar(
	week: Int,
	day: Int,
	weeksNames: Array<String>,
	dayNames: Array<String>,
	pagerState: PagerState,
	dates: List<String>,
	onWeekSelected: (Int) -> Unit,
	onDaySelected: (Int) -> Unit
) {
	val coroutineScope = rememberCoroutineScope()

	Surface(
		modifier = Modifier.fillMaxWidth(),
		elevation = AppBarDefaults.TopAppBarElevation
	) {
		Column {
			TabRow(selectedTabIndex = week) {
				for (weekIndex in 0 until TimetableViewModel.TOTAL_WEEKS) {
					Tab(
						selected = weekIndex == week,
						onClick = { onWeekSelected(weekIndex) },
					) {
						Text(
							text = weeksNames[weekIndex],
							modifier = Modifier.padding(vertical = 15.dp)
						)
					}
				}
			}

			TabRow(
				selectedTabIndex = pagerState.currentPage,
				indicator = { tabPositions ->
					TabRowDefaults.Indicator(
						modifier = if (pagerState.pageCount > 0) Modifier.pagerTabIndicatorOffset(
							pagerState,
							tabPositions
						) else Modifier.tabIndicatorOffset(tabPositions[day])
					)
				}
			) {
				for (dayIndex in 0 until TimetableViewModel.TOTAL_DAYS) {
					Tab(
						selected = dayIndex == pagerState.currentPage,
						onClick = {
							coroutineScope.launch {
								if (pagerState.pageCount > 0)
									pagerState.animateScrollToPage(dayIndex)
							}
							onDaySelected(dayIndex)
						},
					) {
						Column(
							modifier = Modifier.padding(vertical = 5.dp),
							horizontalAlignment = Alignment.CenterHorizontally
						) {
							Text(text = dayNames[dayIndex])
							Spacer(modifier = Modifier.height(3.dp))
							Text(text = dates[dayIndex])
						}
					}
				}
			}
		}
	}
}