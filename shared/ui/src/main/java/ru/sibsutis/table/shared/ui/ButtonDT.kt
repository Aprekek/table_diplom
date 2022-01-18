package ru.sibsutis.table.shared.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ButtonDT(
	title: String,
	onClick: () -> Unit
) {

	Button(
		onClick = onClick,
		modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp)
	) {
		Text(text = title)
	}
}