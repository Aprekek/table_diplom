package ru.sibsutis.table.feature.aboutapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.sibsutis.table.navigation.screens.aboutApp.AboutAppContent
import ru.sibsutis.table.shared.themes.DiplomThemeMode
import ru.sibsutis.table.shared.themes.darkColorTheme
import ru.sibsutis.table.shared.themes.veryLightBlue
import ru.sibsutis.table.shared.ui.ToolbarDT

object AboutAppScreen : AboutAppContent {

	override fun route(navGraphBuilder: NavGraphBuilder, navController: NavController) {
		navGraphBuilder.composable(AboutAppContent.path) {
			Content(navController)
		}
	}

	@Composable
	fun Content(navController: NavController) {
		Scaffold(
			topBar = {
				ToolbarDT(
					title = stringResource(id = R.string.about_app),
					onBackButtonClick = { navController.popBackStack() }
				)
			}
		) {
			Surface(
				modifier = Modifier.fillMaxSize(),
				color = if (DiplomThemeMode.isDarkTheme()) darkColorTheme.surface else veryLightBlue
			) {
				ConstraintLayout(modifier = Modifier.fillMaxSize()) {

					val (image, textAuthors, textArtem, textMisha, sibsutis) = createRefs()

					Icon(
						modifier = Modifier
							.padding(80.dp)
							.constrainAs(image) {
								start.linkTo(parent.start)
								end.linkTo(parent.end)
							},
						painter = painterResource(id = R.drawable.ic_sibsutis_icon),
						contentDescription = null,
						tint = if (DiplomThemeMode.isDarkTheme()) Color.White else Color.Black
					)
					Text(
						modifier = Modifier.constrainAs(textAuthors) {
							top.linkTo(image.bottom)
							start.linkTo(parent.start, margin = 16.dp)
							end.linkTo(parent.end, margin = 16.dp)
						},
						text = stringResource(id = R.string.about_app_author),
						fontWeight = FontWeight.Bold
					)
					Text(
						modifier = Modifier.constrainAs(textArtem) {
							start.linkTo(parent.start, margin = 16.dp)
							end.linkTo(parent.end, margin = 16.dp)
							top.linkTo(textAuthors.bottom, margin = 8.dp)
						},
						text = stringResource(id = R.string.about_app_perov)
					)
					Text(
						modifier = Modifier.constrainAs(textMisha) {
							start.linkTo(parent.start, margin = 16.dp)
							end.linkTo(parent.end, margin = 16.dp)
							top.linkTo(textArtem.bottom)
						},
						text = stringResource(id = R.string.about_app_shamburov)
					)
					Text(
						modifier = Modifier.constrainAs(sibsutis) {
							bottom.linkTo(parent.bottom, margin = 15.dp)
							start.linkTo(parent.start)
							end.linkTo(parent.end)
						},
						text = stringResource(id = R.string.about_app_disclamer)
					)
				}
			}
		}
	}
}