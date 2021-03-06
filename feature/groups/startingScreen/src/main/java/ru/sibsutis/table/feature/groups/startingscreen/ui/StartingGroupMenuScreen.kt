package ru.sibsutis.table.feature.groups.startingscreen.ui

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.koin.androidx.compose.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.get
import ru.sibsutis.table.feature.groups.startingscreen.R
import ru.sibsutis.table.feature.groups.startingscreen.presentation.StartingScreenViewModel
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuContent
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuRouter
import ru.sibsutis.table.shared.group.data.data.mapper.getNames
import ru.sibsutis.table.shared.group.presentation.presentation.GroupMenuScreenState
import ru.sibsutis.table.shared.ui.ButtonDT
import ru.sibsutis.table.shared.ui.EditTextDT
import ru.sibsutis.table.shared.ui.ToolbarDT

object StartingGroupMenuScreen : StartingGroupMenuContent {

	override fun route(navBuilder: NavGraphBuilder, navController: NavController) {
		navBuilder.composable(StartingGroupMenuContent.path) {
			Content(navController = navController)
		}
	}

	@Composable
	private fun Content(navController: NavController) {
		val context = LocalContext.current
		val focusManager = LocalFocusManager.current
		val viewModel by viewModel<StartingScreenViewModel>()

		LaunchedEffect(navController) {
			viewModel.setRouter(
				get(StartingGroupMenuRouter::class.java) {
					parametersOf(navController)
				}
			)
		}

		val state by viewModel.state.collectAsState()
		val list by viewModel.suggestedGroups.collectAsState()
		val selectedGroup by viewModel.selectedGroup.collectAsState()

		var textFieldValue by remember(selectedGroup) {
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

		Scaffold(
			topBar = {
				ToolbarDT(
					title = stringResource(id = R.string.sibsutis_title),
					enableBackButton = false
				)
			}
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(horizontal = 25.dp)
					.verticalScroll(ScrollState(0)),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
			) {

				if (state is GroupMenuScreenState.Loading) {
					CircularProgressIndicator()
				} else {
					EditTextDT(
						expanded = state.isSuggestionsExpanded,
						textFieldValue = textFieldValue,
						labelText = stringResource(id = R.string.group),
						onValueChange = {
							viewModel.onGroupChange(it.text)
							textFieldValue = it
						},
						suggestions = list.getNames(),
						onSuggestionItemClick = { viewModel.onSuggestionItemSelect(it) },
						onDismissRequest = { viewModel.onDismissAction() },
						onKeyboardActionDone = {
							focusManager.clearFocus()
							viewModel.onDismissAction()
						}
					)

					Spacer(modifier = Modifier.height(15.dp))

					ButtonDT(title = stringResource(id = R.string.submit)) {
						viewModel.onSubmitGroupAction()
					}
				}
			}
		}

		DisposableEffect(true)
		{
			onDispose { viewModel.onLeavingComposition() }
		}
	}
}