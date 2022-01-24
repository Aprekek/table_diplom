package ru.sibsutis.table.feature.groups.changegroup.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayoutScope
import ru.sibsutis.table.feature.groups.changegroup.R
import ru.sibsutis.table.feature.groups.changegroup.presentation.ChangeGroupViewModel
import ru.sibsutis.table.shared.group.data.data.mapper.getNames
import ru.sibsutis.table.shared.group.domain.entities.Group
import ru.sibsutis.table.shared.group.presentation.presentation.GroupMenuScreenState
import ru.sibsutis.table.shared.ui.ButtonDT
import ru.sibsutis.table.shared.ui.EditTextDT

@Composable
internal fun MainContent(
	constraintLayoutScope: ConstraintLayoutScope,
	viewModel: ChangeGroupViewModel,
	currentActiveGroup: String,
	state: GroupMenuScreenState,
	textFieldValue: TextFieldValue,
	suggests: List<Group>,
	recentlyWatchedGroups: List<String>
) {
	constraintLayoutScope.apply {

		val (groupTitleRef, groupEditTextRef, submitButtonRef) = createRefs()

		Text(
			text = currentActiveGroup,
			fontSize = 24.sp,
			modifier = Modifier
				.padding(vertical = 40.dp)
				.constrainAs(groupTitleRef) {
					top.linkTo(parent.top)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				}
		)

		EditTextDT(
			expanded = state.isSuggestionsExpanded,
			textFieldValue = textFieldValue,
			labelText = stringResource(id = R.string.group),
			onValueChange = { viewModel.onGroupChange(it) },
			suggestions = suggests.getNames(),
			onSuggestionItemClick = { viewModel.onSuggestionItemSelect(it) },
			onDismissRequest = { viewModel.onDismissAction() },
			modifier = Modifier
				.padding(horizontal = 15.dp)
				.constrainAs(groupEditTextRef) {
					top.linkTo(groupTitleRef.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				}
		)

		if (recentlyWatchedGroups.isNotEmpty()) {
			RecentlyWatchedGroupsContent(
				constraintLayoutScope = this,
				upperContentRef = groupEditTextRef,
				lowerContentRef = submitButtonRef,
				recentlyWatchedGroups = recentlyWatchedGroups,
				onRecentlyWatchedItemClick = { viewModel.onRecentlyWatchedGroupSelect(it) }
			)
		}

		ButtonDT(
			title = stringResource(id = R.string.submit),
			modifier = Modifier
				.padding(top = 20.dp, bottom = 40.dp)
				.constrainAs(submitButtonRef) {
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					if (recentlyWatchedGroups.isEmpty()) {
						top.linkTo(groupEditTextRef.bottom)
					} else {
						bottom.linkTo(parent.bottom)
					}
				}
		) {
			viewModel.onSubmitGroupAction()
		}
	}
}