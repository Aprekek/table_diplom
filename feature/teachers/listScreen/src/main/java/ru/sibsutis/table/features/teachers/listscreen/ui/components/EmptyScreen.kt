package ru.sibsutis.table.features.teachers.listscreen.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.features.teachers.listscreen.R.drawable

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(horizontal = 25.dp)
			.verticalScroll(ScrollState(0)),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Image(
			painterResource(id = drawable.ic_empty),
			colorFilter = ColorFilter.tint(Color.Black),
			contentDescription = "Error image"
		)
		Text(text = "Произошла ошибка")
	}
}

@Composable
@Preview
private fun emptyPrevie(){
	EmptyScreen(Modifier.background(Color.White))
}