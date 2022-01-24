package ru.sibsutis.table.feature.groups.changegroup.ui

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.constraintlayout.compose.ConstraintLayout
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
import ru.sibsutis.table.shared.group.presentation.presentation.GroupMenuScreenState
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
				ToolbarDT(title = stringResource(id = R.string.sibsutis_title)) {
					navController.popBackStack()
				}
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