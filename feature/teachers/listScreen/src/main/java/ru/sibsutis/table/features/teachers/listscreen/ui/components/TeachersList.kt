package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.sibsutis.table.features.teachers.listscreen.R.string
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.shared.ui.ToolbarDT

@ExperimentalFoundationApi
@Composable
fun TeachersList(
	modifier: Modifier = Modifier,
	teachers: Map<Char, List<Teacher>>,
	onTeacherClick: (Teacher) -> Unit = {}
) {

	LazyColumn(
		modifier = modifier.fillMaxSize()
	) {

		teachers.forEach { (header, teacherList) ->
			stickyHeader {
				AlphabetItem(char = header.toString())
			}
			items(count = teacherList.size) { teacherIndex ->
				val teacher = teacherList[teacherIndex]
				TeacherItem(
					teacher = teacher,
					onTeacherClick = onTeacherClick
				)
				Divider()
			}
		}
	}
}

@ExperimentalFoundationApi
@Composable
@Preview
private fun ListPreview() {
	val teachers = listOf(
		Teacher("Храмова ТАТЬЯНА ВИКТРОВНА", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
		Teacher("Конин Максим Васильевич", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
		Teacher("Абрамова Евгения Сергеевна", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
		Teacher("Храмова Антонина Павловна", "89994662475", "email@email.com", "k1 aud 446", "ИВТ", "345"),
	)
	val groupedList = teachers.groupBy { it.name[0] }.toSortedMap()
	Scaffold(topBar = {
		ToolbarDT(
			title = stringResource(id = string.teacher_title),
			enableBackButton = false
		)
	}) {

		TeachersList(
			teachers = groupedList,
			modifier = Modifier.background(Color.White)
		)
	}
}