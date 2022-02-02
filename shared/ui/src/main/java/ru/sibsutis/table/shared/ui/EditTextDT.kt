package ru.sibsutis.table.shared.ui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.shared.themes.DiplomThemeMode
import ru.sibsutis.table.shared.themes.blue700
import ru.sibsutis.table.shared.themes.veryLightBlue

@Composable
fun EditTextDT(
	modifier: Modifier = Modifier,
	expanded: Boolean,
	textFieldValue: TextFieldValue,
	labelText: String,
	onValueChange: (value: TextFieldValue) -> Unit,
	onDismissRequest: () -> Unit,
	suggestions: List<String>,
	onKeyboardActionDone: () -> Unit,
	onSuggestionItemClick: (item: String) -> Unit
) {
	val customTextSelectionColors = if (DiplomThemeMode.isDarkTheme()) {
		TextSelectionColors(
			handleColor = blue700,
			backgroundColor = veryLightBlue
		)
	} else {
		TextSelectionColors(
			handleColor = Color.Gray,
			backgroundColor = Color.LightGray
		)
	}

	ExposedDropdownMenuBox(
		modifier = modifier.fillMaxWidth(),
		expanded = false,
		onExpandedChange = {}
	) {
		CompositionLocalProvider(LocalTextSelectionColors provides customTextSelectionColors) {
			OutlinedTextField(
				value = textFieldValue,
				placeholder = { Text(text = labelText, color = Color.Gray) },
				onValueChange = { onValueChange(it) },
				modifier = Modifier.fillMaxWidth(),
				keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
				keyboardActions = KeyboardActions(onDone = {
					onKeyboardActionDone()
				}),
				shape = RoundedCornerShape(15.dp),
				colors = TextFieldDefaults.textFieldColors(
					textColor = Color.Black,
					backgroundColor = if (DiplomThemeMode.isDarkTheme()) Color.LightGray else veryLightBlue,
					cursorColor = if (DiplomThemeMode.isDarkTheme()) blue700 else Color.Gray,
					focusedIndicatorColor = Color.White,
					unfocusedIndicatorColor = Color.LightGray,
				)
			)
		}

		if (suggestions.isNotEmpty() && expanded) {
			ExposedDropdownMenu(
				expanded = expanded,
				onDismissRequest = { onDismissRequest() },
				modifier = Modifier.defaultMinSize(minHeight = 48.dp)
			) {
				suggestions.forEach { suggestionValue ->
					DropdownMenuItem(
						modifier = Modifier.fillMaxWidth(),
						onClick = { onSuggestionItemClick(suggestionValue) }
					) { Text(text = suggestionValue) }
				}
			}
		}
	}
}