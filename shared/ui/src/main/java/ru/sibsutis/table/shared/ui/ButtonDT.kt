package ru.sibsutis.table.shared.ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ButtonDT(
	title: String,
	onClick: () -> Unit
) {

	Button(
		onClick = onClick,
	) {
		Text(text = title)
	}
}