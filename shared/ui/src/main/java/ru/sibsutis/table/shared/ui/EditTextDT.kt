package ru.sibsutis.table.shared.ui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun EditTextDT(
	text: String,
	onValueChange: (value: String) -> Unit,
	suggestions: List<String>,
	onSuggestionItemClick: (item: String) -> Unit
) {

	var expanded by rememberSaveable { mutableStateOf(false) }

	ExposedDropdownMenuBox(
		expanded = text.isNotBlank(),
		onExpandedChange = {}
	) {
		OutlinedTextField(
			value = text,
			onValueChange = {
				onValueChange(it)
				expanded = it.isNotBlank()
			},
			modifier = Modifier.fillMaxWidth()
		)

		ExposedDropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false },
			modifier = Modifier.defaultMinSize(minHeight = 48.dp)
		) {
			suggestions.forEach { suggestionValue ->
				DropdownMenuItem(
					modifier = Modifier.fillMaxWidth(),
					onClick = {
						onSuggestionItemClick(suggestionValue)
						expanded = false
					}
				) {
					Text(text = suggestionValue)
				}
			}
		}
	}

	DisposableEffect(Unit) {
		onDispose { expanded = false }
	}
}