package ru.sibsutis.table.features.teachers.listscreen.ui.components

import SearchAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.sibsutis.table.features.teachers.listscreen.R
import ru.sibsutis.table.features.teachers.listscreen.presentation.SearchWidgetState
import ru.sibsutis.table.shared.ui.ToolbarDT

@Composable
fun MainAppBar(
	searchWidgetState: SearchWidgetState,
	searchTextState: String,
	onTextChange: (String) -> Unit,
	onCloseClicked: () -> Unit,
	onSearchClicked: (String) -> Unit,
	onSearchTriggered: () -> Unit
) {
	when (searchWidgetState) {
		SearchWidgetState.CLOSED -> {
			ToolbarDT(
				title = stringResource(id = R.string.teacher_title),
				enableBackButton = false,
				enableSearchButton = true,
				onSearchButtonClick = onSearchTriggered
			)
		}

		SearchWidgetState.OPENED -> {
			SearchAppBar(
				text = searchTextState,
				onTextChange = onTextChange,
				onCloseClicked = onCloseClicked,
				onSearchClicked = onSearchClicked
			)
		}
	}
}