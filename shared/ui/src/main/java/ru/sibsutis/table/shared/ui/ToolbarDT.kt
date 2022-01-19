package ru.sibsutis.table.shared.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun ToolbarDT(
	title: String,
	enableBackButton: Boolean = true,
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
		} else null
	)
}