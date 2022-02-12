package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher

@Composable
fun TeachersList(
	modifier: Modifier = Modifier,
	teachers: Map<Char, List<Teacher>>,
	onTeacherClick: (Teacher) -> Unit = {},
	firstElement: Int,
	offsetElement: Int,
	onLeavingComposition: (firstElement: Int, offsetElement: Int) -> Unit = { _: Int, _: Int -> }
) {

	val listState = rememberLazyListState(firstElement, offsetElement)

	LazyColumn(
		modifier = modifier.fillMaxSize(),
		state = listState
	) {

		teachers.forEach { (header, teacherList) ->
			stickyHeader {
				AlphabetItem(char = header.toString())
			}
			items(count = teacherList.size) { teacherIndex ->
				val teacher = teacherList[teacherIndex]

				if (teacherIndex == 0)
					Divider()
				TeacherItem(
					teacher = teacher,
					onTeacherClick = onTeacherClick
				)
				Divider()
			}
		}
	}
	DisposableEffect(true) {
		onDispose {
			onLeavingComposition(
				listState.firstVisibleItemIndex,
				listState.firstVisibleItemScrollOffset
			)
		}
	}
}