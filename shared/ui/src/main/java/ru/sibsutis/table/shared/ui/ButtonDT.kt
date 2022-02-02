package ru.sibsutis.table.shared.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonDT(
	modifier: Modifier = Modifier,
	title: String,
	onClick: () -> Unit
) {

	Button(
		modifier = modifier.fillMaxWidth(),
		shape = RoundedCornerShape(15.dp),
		onClick = onClick,
	) {
		Text(
			text = title,
			color = Color.White,
			modifier = Modifier.padding(vertical = 10.dp)
		)
	}
}