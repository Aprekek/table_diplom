package ru.sibsutis.table.feature.groupmenu.ui

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import ru.sibsutis.table.feature.groupmenu.GroupMenuRouter
import ru.sibsutis.table.feature.groupmenu.R
import ru.sibsutis.table.feature.groupmenu.data.mapper.getNames
import ru.sibsutis.table.feature.groupmenu.presentation.StarterScreenState
import ru.sibsutis.table.feature.groupmenu.presentation.StarterScreenViewModel
import ru.sibsutis.table.shared.ui.ButtonDT
import ru.sibsutis.table.shared.ui.EditTextDT
import ru.sibsutis.table.shared.ui.ToolbarDT

object GroupMenuScreen {

	private const val PATH = "group_menu"

	fun path() = PATH

	fun route(navBuilder: NavGraphBuilder, navController: NavController) {
		navBuilder.composable(PATH) {
			Content(navController = navController)
		}
	}

	@Composable
	private fun Content(navController: NavController) {
		val context = LocalContext.current

		val viewModel by viewModel<StarterScreenViewModel>()

		LaunchedEffect(navController) {
			val router = get<GroupMenuRouter>(GroupMenuRouter::class.java) { parametersOf(navController) }
			viewModel.setRouter(router)
		}

		val state by viewModel.state.collectAsState()
		val list by viewModel.suggestedGroups.collectAsState()
		val selectedGroup by viewModel.selectedGroup.collectAsState()

		val textFieldValue by remember(selectedGroup) {
			mutableStateOf(
				TextFieldValue(
					text = selectedGroup,
					selection = TextRange(selectedGroup.length)
				)
			)
		}

		if (state is StarterScreenState.NoGroupError) {
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

				if (state is StarterScreenState.Loading) {
					CircularProgressIndicator()
				} else {
					EditTextDT(
						expanded = state.isSuggestionsExpanded,
						textFieldValue = textFieldValue,
						labelText = stringResource(id = R.string.group),
						onValueChange = { viewModel.onGroupChange(it) },
						suggestions = list.getNames(),
						onSuggestionItemClick = { viewModel.onSuggestionItemSelect(it) },
						onDismissRequest = { viewModel.onDismissAction() }
					)

					ButtonDT(title = stringResource(id = R.string.submit))
					{
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