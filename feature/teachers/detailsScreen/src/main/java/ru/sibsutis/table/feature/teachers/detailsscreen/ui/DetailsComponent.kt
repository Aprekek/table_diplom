package ru.sibsutis.table.feature.teachers.detailsscreen.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage
import ru.sibsutis.table.feature.teachers.detailsscreen.R
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.TeacherDetailsState
import ru.sibsutis.table.shared.themes.DiplomThemeMode
import ru.sibsutis.table.shared.themes.blue700
import ru.sibsutis.table.shared.themes.grey1Dp
import ru.sibsutis.table.shared.themes.lightColorTheme

@Composable
fun MainContent(
	state: TeacherDetailsState.Content
) {
	val context = LocalContext.current
	val color = if (DiplomThemeMode.isDarkTheme()) grey1Dp else lightColorTheme.surface

	SelectionContainer {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.verticalScroll(ScrollState(0)),
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			Surface(
				modifier = Modifier
					.padding(vertical = 32.dp),

				elevation = 3.dp,
				shape = CircleShape,
			) {
				GlideImage(
					modifier = Modifier.size(200.dp),
					imageModel = state.teacher.photo,
					placeHolder = painterResource(id = R.drawable.ic_empty_people),
					error = painterResource(id = R.drawable.ic_empty_people)
				)
			}

			AttributeItem(
				title = stringResource(id = R.string.details_name),
				description = state.teacher.name,
				backgroundColor = color
			)

			Spacer(modifier = Modifier.height(15.dp))

			AttributeItem(
				title = stringResource(id = R.string.details_phone),
				description = state.teacher.phone,
				backgroundColor = color
			)

			Spacer(modifier = Modifier.height(15.dp))

			AttributeItem(
				title = stringResource(id = R.string.details_email),
				description = AnnotatedString(
					text = state.teacher.email,
					spanStyle = SpanStyle(color = blue700, textDecoration = TextDecoration.Underline)
				),
				backgroundColor = color,
				descriptionModifier = Modifier
					.clickable(enabled = true, role = Role.Button) {
						(context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
							setPrimaryClip(ClipData.newPlainText(state.teacher.email, state.teacher.email))
						}
						Toast
							.makeText(context, context.getString(R.string.details_copy), Toast.LENGTH_SHORT)
							.show()
					},
			)

			Spacer(modifier = Modifier.height(15.dp))

			AttributeItem(
				title = stringResource(id = R.string.details_address),
				description = state.teacher.address,
				backgroundColor = color
			)

			Spacer(modifier = Modifier.height(15.dp))

			AttributeItem(
				title = stringResource(id = R.string.details_cathedra),
				description = state.teacher.cathedra,
				backgroundColor = color
			)
		}
	}
}

@Composable
fun AttributeItem(
	descriptionModifier: Modifier = Modifier,
	title: String,
	description: String,
	backgroundColor: Color
) {
	Surface(
		modifier = Modifier.fillMaxWidth(),
		color = backgroundColor
	) {
		Column(
			modifier = Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Divider()
			Column(
				modifier = Modifier.padding(vertical = 5.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(
					fontWeight = FontWeight.Bold,
					text = title
				)
				Text(
					modifier = descriptionModifier,
					textAlign = TextAlign.Center,
					text = description
				)
			}
			Divider()
		}
	}
}

@Composable
fun AttributeItem(
	descriptionModifier: Modifier = Modifier,
	title: String,
	description: AnnotatedString,
	backgroundColor: Color
) {
	Surface(
		modifier = Modifier.fillMaxWidth(),
		color = backgroundColor
	) {
		Column(
			modifier = Modifier.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Divider()
			Column(
				modifier = Modifier.padding(vertical = 5.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(
					fontWeight = FontWeight.Bold,
					text = title
				)
				Text(
					modifier = descriptionModifier,
					textAlign = TextAlign.Center,
					text = description
				)
			}
			Divider()
		}
	}
}