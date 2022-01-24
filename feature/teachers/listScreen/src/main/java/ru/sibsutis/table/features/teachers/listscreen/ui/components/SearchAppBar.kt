import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.features.teachers.listscreen.R

@Composable
fun SearchAppBar(
	text: String,
	onTextChange: (String) -> Unit,
	onCloseClicked: () -> Unit,
	onSearchClicked: (String) -> Unit,
) {
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.height(56.dp),
		elevation = AppBarDefaults.TopAppBarElevation,
		color = MaterialTheme.colors.primary
	) {
		TextField(
			modifier = Modifier.fillMaxWidth(),
			value = text,
			onValueChange = {
				onTextChange(it)
			},
			placeholder = {
				Text(
					modifier = Modifier.alpha(ContentAlpha.medium),
					text = stringResource(id = R.string.search),
					color = Color.White
				)
			},
			textStyle = TextStyle(
				fontSize = MaterialTheme.typography.subtitle1.fontSize
			),
			singleLine = true,
			leadingIcon = {
				IconButton(
					modifier = Modifier.alpha(ContentAlpha.medium),
					onClick = {}
				) {
					Icon(
						imageVector = Icons.Default.Search,
						contentDescription = null,
						tint = Color.White
					)
				}
			},
			trailingIcon = {
				IconButton(
					onClick = {
						if (text.isNotEmpty()) {
							onTextChange("")
						} else {
							onCloseClicked()
						}
					}
				) {
					Icon(
						imageVector = Icons.Default.Close,
						contentDescription = null,
						tint = Color.White
					)
				}
			},
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Search
			),
			keyboardActions = KeyboardActions(
				onSearch = {
					onSearchClicked(text)
				}
			),
			colors = TextFieldDefaults.textFieldColors(
				backgroundColor = Color.Transparent,
				cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
			))
	}
}

@Composable
@Preview
fun SearchAppBarPreview() {
	SearchAppBar(
		text = "Some random text",
		onTextChange = {},
		onCloseClicked = {},
		onSearchClicked = {}
	)
}