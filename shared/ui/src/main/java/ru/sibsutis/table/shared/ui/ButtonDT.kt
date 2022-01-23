package ru.sibsutis.table.shared.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonDT(
	modifier: Modifier = Modifier,
	title: String,
	onClick: () -> Unit
) {

	Button(
		modifier = modifier,
		onClick = onClick,
	) {
		Text(text = title)
	}
}