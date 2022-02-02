package ru.sibsutis.table.feature.groups.changegroup.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import ru.sibsutis.table.feature.groups.changegroup.R

@Composable
internal fun RecentlyWatchedGroupsContent(
	constraintLayoutScope: ConstraintLayoutScope,
	upperContentRef: ConstrainedLayoutReference,
	lowerContentRef: ConstrainedLayoutReference,
	recentlyWatchedGroups: List<String>,
	onRecentlyWatchedItemClick: (String) -> Unit
) {
	constraintLayoutScope.apply {
		val recentGroupsRef = createRef()

		Column(
			modifier = Modifier
				.constrainAs(recentGroupsRef) {
					top.linkTo(upperContentRef.bottom)
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					bottom.linkTo(lowerContentRef.top)
					width = Dimension.fillToConstraints
					height = Dimension.fillToConstraints
				}
				.padding(top = 25.dp)
				.verticalScroll(ScrollState(0))
		) {
			Text(
				text = stringResource(R.string.recently_watched_groups),
				fontSize = 16.sp,
				modifier = Modifier.padding(start = 15.dp, bottom = 10.dp)
			)

			recentlyWatchedGroups.forEachIndexed { index, text ->
				RecentlyWatchedItem(text = text, onClick = { onRecentlyWatchedItemClick(it) })
				if (index == recentlyWatchedGroups.lastIndex)
					Divider()
			}
		}
	}
}