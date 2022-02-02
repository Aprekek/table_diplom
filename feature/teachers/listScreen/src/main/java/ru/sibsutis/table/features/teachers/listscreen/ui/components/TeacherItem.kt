package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher

@Composable
fun TeacherItem(
	modifier: Modifier = Modifier,
	teacher: Teacher,
	onTeacherClick: (Teacher) -> Unit = {}
) {
	Surface(modifier = modifier.fillMaxWidth()) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.clickable { onTeacherClick(teacher) }
				.padding(8.dp)
		) {
			Text(text = teacher.name)

			Spacer(modifier = Modifier.height(8.dp))

			CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
				Text(
					text = teacher.email,
					modifier = Modifier.align(Alignment.End),
				)
			}
		}
	}
}

@Preview
@Composable
private fun TeacherPreview() {
	TeacherItem(
		modifier = Modifier.background(Color.White),
		teacher = Teacher("Храмова ТАТЬЯНА ВИКТРОВНА", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345")
	)
}
