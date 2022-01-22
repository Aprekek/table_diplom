package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun AlphabetItem(
	char: String,
	modifier: Modifier = Modifier,
	modifierText: Modifier = Modifier,
	fontWeight: FontWeight = FontWeight.Light,
	fontSize: TextUnit = 10.sp,
	fontStyle: FontStyle = FontStyle.Italic
) {
	Row(
		modifier = modifier
			.background(Color.LightGray)
			.fillMaxWidth()
	) {
		Text(
			modifier = modifierText.padding(start = 8.dp),
			fontWeight = fontWeight,
			fontSize = fontSize,
			fontStyle = fontStyle,
			text = char.uppercase(Locale.getDefault())
		)
	}
}

@Composable
@Preview
private fun AlphabetPreview() {
	AlphabetItem(char = "а")
}