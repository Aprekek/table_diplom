package ru.sibsutis.table.feature.timetable.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson

@Composable
internal fun LessonsList(
	lessons: List<Lesson>,
	localizedTypes: Array<String>
) {
	val listState = rememberLazyListState()

	LazyColumn(
		state = listState,
		modifier = Modifier.fillMaxSize()
	) {

		item {
			LessonCard(
				lesson = lessons.first(),
				localizedTypes = localizedTypes,
				modifier = Modifier.padding(vertical = 10.dp)
			)
		}

		for (i in 1 until lessons.size) {
			item {
				LessonCard(
					lesson = lessons[i],
					localizedTypes = localizedTypes,
					modifier = Modifier.padding(bottom = 10.dp)
				)
			}
		}
	}
}