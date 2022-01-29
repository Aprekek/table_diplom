package ru.sibsutis.table.feature.timetable.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.sibsutis.table.feature.timetable.R

@Composable
internal fun NoLessonsScreen() {
	Column(
		modifier = Modifier.fillMaxSize(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = painterResource(id = R.drawable.confetti),
			contentDescription = null,
			modifier = Modifier.size(200.dp),
			contentScale = ContentScale.Crop
		)
		Spacer(modifier = Modifier.height(25.dp))
		Text(text = stringResource(id = R.string.day_off))
	}
}