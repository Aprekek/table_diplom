package ru.sibsutis.table.feature.groups.changegroup.ui

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.sibsutis.table.feature.groups.changegroup.R
import ru.sibsutis.table.feature.groups.changegroup.presentation.ChangeGroupViewModel
import ru.sibsutis.table.navigation.screens.changegroup.ChangeGroupNavigationContent
import ru.sibsutis.table.navigation.screens.changegroup.ChangeGroupNavigationContent.Companion.path
import ru.sibsutis.table.navigation.screens.changegroup.ChangeGroupNavigationRouter
import ru.sibsutis.table.shared.group.data.data.mapper.getNames
import ru.sibsutis.table.shared.group.domain.entities.Group
import ru.sibsutis.table.shared.group.presentation.presentation.GroupMenuScreenState
import ru.sibsutis.table.shared.ui.ButtonDT
import ru.sibsutis.table.shared.ui.EditTextDT
import ru.sibsutis.table.shared.ui.LoadingScreen
import ru.sibsutis.table.shared.ui.ToolbarDT

object ChangeGroupScreen : ChangeGroupNavigationContent {

	override fun route(navGraphBuilder: NavGraphBuilder, navController: NavController) {
		navGraphBuilder.composable(path) {
			val currentActiveGroup = it.arguments!!.getString(ChangeGroupNavigationContent.Arguments.GROUP_NAME)!!
			Content(navController, currentActiveGroup)
		}
	}

	@Composable
	private fun Content(navController: NavController, currentActiveGroup: String) {
		val context = LocalContext.current
		val viewModel by viewModel<ChangeGroupViewModel> {
			parametersOf(currentActiveGroup)
		}

		val state by viewModel.state.collectAsState()
		val suggests by viewModel.suggestedGroups.collectAsState()
		val selectedGroup by viewModel.selectedGroup.collectAsState()
		val recentlyWatchedGroups by viewModel.recentlyWatchedGroups.collectAsState()

		val textFieldValue by remember(selectedGroup) {
			mutableStateOf(
				TextFieldValue(
					text = selectedGroup,
					selection = TextRange(selectedGroup.length)
				)
			)
		}

		if (state is GroupMenuScreenState.NoGroupError) {
			LaunchedEffect(true) {
				viewModel.noGroupErrorWasShown()
				Toast.makeText(context, context.getString(R.string.group_not_found), Toast.LENGTH_LONG).show()
			}
		}

		LaunchedEffect(navController) {
			viewModel.setRouter(
				get(ChangeGroupNavigationRouter::class.java) {
					parametersOf(navController)
				}
			)
		}

		Scaffold(
			modifier = Modifier.fillMaxSize(),
			topBar = {
				ToolbarDT(
					title = stringResource(id = R.string.sibsutis_title),
					enableBackButton = false
				)
			}
		) {

			ConstraintLayout(
				modifier = Modifier
					.fillMaxSize()
					.verticalScroll(ScrollState(0))
			) {
				val circularProgressIndRef = createRef()

				if (state is GroupMenuScreenState.Loading) {
					LoadingScreen(
						modifier = Modifier.constrainAs(circularProgressIndRef) {
							top.linkTo(parent.top)
							bottom.linkTo(parent.bottom)
							start.linkTo(parent.start)
							end.linkTo(parent.end)
						}
					)
				} else {
					MainContent(
						constraintLayoutScope = this,
						viewModel = viewModel,
						currentActiveGroup = currentActiveGroup,
						state = state,
						textFieldValue = textFieldValue,
						suggests = suggests,
						recentlyWatchedGroups = recentlyWatchedGroups
					)
				}
			}
		}

		DisposableEffect(true)
		{
			onDispose { viewModel.onLeavingComposition() }
		}
	}
}