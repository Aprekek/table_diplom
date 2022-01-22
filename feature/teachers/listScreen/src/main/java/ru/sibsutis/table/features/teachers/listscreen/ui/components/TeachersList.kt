package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.features.teachers.listscreen.R.string
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.shared.ui.ToolbarDT

@Composable
fun TeachersList(
	modifier: Modifier = Modifier,
	teachers: List<Teacher>,
	onTeacherClick: (Teacher) -> Unit = {}
) {

	LazyColumn(
		modifier = modifier
			.fillMaxSize()
			.padding(vertical = 2.dp)
	) {
		items(count = teachers.size) { teacherIndex ->
			val teacher = teachers[teacherIndex]
			TeacherItem(
				teacher = teacher,
				onTeacherClick = onTeacherClick
			)
			Divider()
		}
	}
}

@Composable
@Preview
private fun ListPreview() {
	val teachers = listOf(
		Teacher("Храмова ТАТЬЯНА ВИКТРОВНА", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
		Teacher("Храмова ТАТЬЯНА ВИКТРОВНА", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
		Teacher("Храмова ТАТЬЯНА ВИКТРОВНА", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
		Teacher("Храмова ТАТЬЯНА ВИКТРОВНА", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
	)
	Scaffold(topBar = {
		ToolbarDT(
			title = stringResource(id = string.teacher_title),
			enableBackButton = false
		)
	}) {

		TeachersList(
			teachers = teachers,
			modifier = Modifier.background(Color.White)
		)
	}
}