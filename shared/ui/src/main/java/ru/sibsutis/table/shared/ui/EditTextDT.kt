package ru.sibsutis.table.shared.ui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun EditTextDT(
	expanded: Boolean,
	textFieldValue: TextFieldValue,
	labelText: String,
	onValueChange: (value: String) -> Unit,
	onDismissRequest: () -> Unit,
	suggestions: List<String>,
	onSuggestionItemClick: (item: String) -> Unit
) {
	ExposedDropdownMenuBox(
		expanded = textFieldValue.text.isNotBlank(),
		onExpandedChange = {}
	) {
		OutlinedTextField(
			value = textFieldValue,
			label = { Text(text = labelText) },
			onValueChange = {
				onValueChange(it.text)
			},
			modifier = Modifier.fillMaxWidth(),
			keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
		)

		if (suggestions.isNotEmpty() && expanded) {
			ExposedDropdownMenu(
				expanded = expanded,
				onDismissRequest = { onDismissRequest() },
				modifier = Modifier.defaultMinSize(minHeight = 48.dp)
			) {
				suggestions.forEach { suggestionValue ->
					DropdownMenuItem(
						modifier = Modifier.fillMaxWidth(),
						onClick = {
							onSuggestionItemClick(suggestionValue)
						}
					) {
						Text(text = suggestionValue)
					}
				}
			}
		}
	}
}