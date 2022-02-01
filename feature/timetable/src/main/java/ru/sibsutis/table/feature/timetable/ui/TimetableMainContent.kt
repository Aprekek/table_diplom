package ru.sibsutis.table.feature.timetable.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ru.sibsutis.table.feature.timetable.R
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson
import ru.sibsutis.table.shared.themes.DiplomThemeMode
import ru.sibsutis.table.shared.themes.darkColorTheme
import ru.sibsutis.table.shared.themes.veryLightBlue

@Composable
internal fun TimetableMainContent(
	modifier: Modifier = Modifier,
	context: Context,
	pagerState: PagerState,
	pagesCount: Int,
	lessons: Map<Int, List<Lesson>>
) {
	val localizedTypes = remember {
		context.resources.getStringArray(R.array.lesson_types)
	}

	HorizontalPager(
		count = pagesCount,
		state = pagerState,
		modifier = modifier
	) { page ->
		val lessonsForDay = lessons[page]

		Surface(
			modifier = Modifier.fillMaxSize(),
			color = if (DiplomThemeMode.isDarkTheme()) darkColorTheme.surface else veryLightBlue
		) {
			if (lessonsForDay.isNullOrEmpty()) {
				NoLessonsScreen()
			} else {
				LessonsList(lessons = lessonsForDay, localizedTypes = localizedTypes)
			}
		}
	}
}
