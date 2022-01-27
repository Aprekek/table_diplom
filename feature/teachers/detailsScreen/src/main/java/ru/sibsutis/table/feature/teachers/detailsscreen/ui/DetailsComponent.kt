package ru.sibsutis.table.feature.teachers.detailsscreen.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import androidx.constraintlayout.compose.ConstraintLayout
import com.skydoves.landscapist.glide.GlideImage
import ru.sibsutis.table.feature.teachers.detailsscreen.R
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.TeacherDetailsState

@Composable
fun MainContent(
	state: TeacherDetailsState.Content
) {

	val context = LocalContext.current

	ConstraintLayout(modifier = Modifier.fillMaxSize()) {

		val (
			image,
			name, nameContent,
			phone, phoneContent,
			email, emailContent,
			address, addressContent,
			cathedra, cathedraContent
		) = createRefs()

		Surface(
			modifier = Modifier
				.padding(top = 8.dp)
				.constrainAs(image) {
					start.linkTo(parent.start)
					end.linkTo(parent.end)
				},
			elevation = 15.dp,
			shape = CircleShape,
		) {
			GlideImage(
				modifier = Modifier.size(200.dp),
				imageModel = state.teacher.photo,
				placeHolder = painterResource(id = R.drawable.ic_empty_people),
				error = painterResource(id = R.drawable.ic_empty_people)
			)
		}

		Text(
			modifier = Modifier.constrainAs(name) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(image.bottom, 32.dp)
			},
			fontWeight = FontWeight.Bold,
			text = stringResource(id = R.string.details_name)
		)
		Text(
			modifier = Modifier.constrainAs(nameContent) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(name.bottom)
			},
			text = state.teacher.name
		)
		Text(
			modifier = Modifier.constrainAs(phone) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(nameContent.bottom, 8.dp)
			},
			fontWeight = FontWeight.Bold,
			text = stringResource(id = R.string.details_phone)
		)
		SelectionContainer(
			modifier = Modifier.constrainAs(phoneContent) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(phone.bottom)
			},
		) {
			Text(text = state.teacher.phone)
		}
		Text(
			modifier = Modifier.constrainAs(email) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(phoneContent.bottom, 8.dp)
			},
			fontWeight = FontWeight.Bold,
			text = stringResource(id = R.string.details_email)
		)
		Text(
			modifier = Modifier
				.clickable(
					enabled = true,
					role = Role.Button
				) {
					(context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager).apply {
						setPrimaryClip(ClipData.newPlainText(state.teacher.email, state.teacher.email))
					}
					Toast
						.makeText(context, context.getString(R.string.details_copy), Toast.LENGTH_SHORT)
						.show()
				}
				.constrainAs(emailContent) {
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					top.linkTo(email.bottom)
				},
			text = AnnotatedString(
				text = state.teacher.email,
				spanStyle = SpanStyle(color = Color.Companion.Blue, textDecoration = TextDecoration.Underline)
			),
		)
		Text(
			modifier = Modifier.constrainAs(address) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(emailContent.bottom, 8.dp)
			},
			fontWeight = FontWeight.Bold,
			text = stringResource(id = R.string.details_address)
		)
		Text(
			modifier = Modifier.constrainAs(addressContent) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(address.bottom)
			},
			text = state.teacher.address
		)
		Text(
			modifier = Modifier.constrainAs(cathedra) {
				start.linkTo(parent.start)
				end.linkTo(parent.end)
				top.linkTo(addressContent.bottom, 8.dp)
			},
			fontWeight = FontWeight.Bold,
			text = stringResource(id = R.string.details_cathedra)
		)
		Text(
			modifier = Modifier
				.constrainAs(cathedraContent) {
					start.linkTo(parent.start)
					end.linkTo(parent.end)
					top.linkTo(cathedra.bottom)
				},
			textAlign = TextAlign.Center,
			text = state.teacher.cathedra
		)
	}
}