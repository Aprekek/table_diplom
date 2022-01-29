package ru.sibsutis.table.feature.timetable.ui

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import ru.sibsutis.table.feature.timetable.R
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson

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
			color = MaterialTheme.colors.primary.copy(alpha = 0.03f)
		) {
			if (lessonsForDay.isNullOrEmpty()) {
				NoLessonsScreen()
			} else {
				LessonsList(lessons = lessonsForDay, localizedTypes = localizedTypes)
			}
		}
	}
}
