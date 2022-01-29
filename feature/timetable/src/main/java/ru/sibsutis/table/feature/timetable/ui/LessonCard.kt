package ru.sibsutis.table.feature.timetable.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.feature.timetable.R
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson

@Composable
internal fun LessonCard(
	modifier: Modifier = Modifier,
	lesson: Lesson,
	localizedTypes: Array<String>
) {
	val contentPadding = remember { 8.dp }

	Surface(modifier = modifier.fillMaxWidth()) {
		Column {
			Divider()
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						start = contentPadding,
						top = contentPadding,
						end = contentPadding
					)
			) {
				Text(
					text = lesson.name,
					fontWeight = FontWeight.Bold
				)
				Text(
					text = lesson.teacher,
					color = Color.Gray
				)
				Text(
					text = localizedTypes[lesson.type.value],
					color = Color.DarkGray,
					modifier = Modifier.align(Alignment.End)
				)
			}
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.padding(bottom = contentPadding)
			) {
				Text(
					text = lesson.address,
					color = Color.DarkGray,
					modifier = Modifier
						.align(Alignment.CenterStart)
						.padding(horizontal = contentPadding)
				)
				Surface(
					shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp),
					color = Color.LightGray,
					modifier = Modifier.align(Alignment.CenterEnd)
				) {
					Text(
						text = stringResource(
							id = R.string.lesson_time_period,
							formatArgs = arrayOf(lesson.startTime, lesson.endTime)
						),
						modifier = Modifier.padding(
							start = contentPadding,
							top = 5.dp,
							end = contentPadding,
							bottom = 5.dp
						)
					)
				}
			}
			Divider()
		}
	}
}