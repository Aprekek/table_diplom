package ru.sibsutis.table.feature.groups.changegroup.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.table.feature.groups.changegroup.R

@Composable
internal fun RecentlyWatchedItem(
	text: String,
	onClick: (String) -> Unit
) {
	Column(
		modifier = Modifier.clickable { onClick(text) },
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(color = MaterialTheme.colors.primary)
				.padding(vertical = 15.dp, horizontal = 15.dp)
		) {

			Text(
				text = text,
				fontSize = 16.sp,
				color = Color.White,
				modifier = Modifier.align(Alignment.CenterStart)
			)

			Icon(
				painter = painterResource(id = R.drawable.ic_entry),
				contentDescription = null,
				modifier = Modifier.align(Alignment.CenterEnd),
				tint = Color.White
			)
		}
		Divider(color = Color.White)
	}
}