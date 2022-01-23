package ru.sibsutis.table.shared.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorScreen(
	modifier: Modifier = Modifier,
	reload: () -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 25.dp)
			.verticalScroll(ScrollState(0)),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Image(
			painterResource(id = R.drawable.ic_error),
			colorFilter = ColorFilter.tint(Color.Black),
			contentDescription = "Error image"
		)
		Text(text = stringResource(id = R.string.error))
		Button(modifier = Modifier.padding(20.dp), onClick = reload) {
			Text(
				text = stringResource(id = R.string.reload),
				fontSize = 15.sp
			)
		}
	}
}

@Composable
@Preview
private fun ErrorPreview() {
	ErrorScreen(Modifier.background(Color.White)) {}
}