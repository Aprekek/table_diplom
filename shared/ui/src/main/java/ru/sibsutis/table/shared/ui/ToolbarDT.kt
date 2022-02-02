package ru.sibsutis.table.shared.ui

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun ToolbarDT(
	title: String,
	enableBackButton: Boolean = true,
	enableSearchButton: Boolean = false,
	onSearchButtonClick: () -> Unit = {},
	elevation: Dp = AppBarDefaults.TopAppBarElevation,
	backgroundColor: Color = MaterialTheme.colors.primarySurface,
	onBackButtonClick: () -> Unit = {}
) {
	TopAppBar(
		title = { Text(text = title) },
		navigationIcon =
		if (enableBackButton) {
			{
				IconButton(onClick = onBackButtonClick) {
					Icon(
						painter = painterResource(id = R.drawable.back_button_icon),
						contentDescription = null
					)
				}
			}
		} else null,
		actions = {
			if (enableSearchButton) {
				IconButton(
					modifier = Modifier,
					onClick = { onSearchButtonClick() }) {
					Icon(
						imageVector = Icons.Filled.Search,
						contentDescription = null
					)
				}
			}
		},
		elevation = elevation,
		backgroundColor = backgroundColor
	)
}