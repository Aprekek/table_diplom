package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

	Box(
		modifier = modifier
			.fillMaxWidth()
			.padding(8.dp)
			.clickable { onTeacherClick(teacher) }
	) {
		Text(text = teacher.name, modifier = Modifier.align(Alignment.TopStart))
		Column(
			modifier = modifier.align(Alignment.TopEnd),
			horizontalAlignment = Alignment.End
		) {
			// TODO переделать на факультет
			Text(text = teacher.address)
			Text(text = teacher.email)
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
