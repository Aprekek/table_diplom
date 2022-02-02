package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.sibsutis.table.shared.themes.DiplomThemeMode
import ru.sibsutis.table.shared.themes.grey1Dp
import ru.sibsutis.table.shared.themes.veryLightBlue
import java.util.*

@Composable
fun AlphabetItem(
	char: String,
	modifier: Modifier = Modifier,
	modifierText: Modifier = Modifier,
	fontWeight: FontWeight = FontWeight.Bold,
	fontSize: TextUnit = 10.sp
) {
	Surface(
		modifier = modifier.fillMaxWidth(),
		color = if (DiplomThemeMode.isDarkTheme())
			grey1Dp
		else
			veryLightBlue
	) {
		Text(
			modifier = modifierText.padding(
				horizontal = 8.dp,
				vertical = 1.dp
			),
			fontWeight = fontWeight,
			fontSize = fontSize,
			text = char.uppercase(Locale.getDefault())
		)
	}
}

@Composable
@Preview
private fun AlphabetPreview() {
	AlphabetItem(char = "а")
}