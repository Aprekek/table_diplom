package ru.sibsutis.table.feature.groups.startingscreen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sibsutis.table.feature.groups.startingscreen.domain.entities.Group
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.UpdateLocalGroupStorageUseCase
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuRouter

open class StartingGroupMenuScreenViewModel(
	private val getGroupsListUseCase: GetGroupsListUseCase,
	private val updateLocalGroupStorageUseCase: UpdateLocalGroupStorageUseCase,
	private val isGroupExistUseCase: IsGroupExistUseCase,
	private val updateCurrentGroupInPreferencesUseCase: UpdateCurrentGroupInPreferencesUseCase
) : ViewModel() {

	private lateinit var router: StartingGroupMenuRouter

	private val debounceTime = 200L
	private var localStorageGroupsChangesJob: Job? = null

	private var _state =
		MutableStateFlow<StartingGroupMenuScreenState>(StartingGroupMenuScreenState.NoError(isSuggestionsExpanded = true))
	val state = _state.asStateFlow()

	private var onSuggestionItemPerform = false
		get() {
			val t = field
			field = false
			return t
		}
	private var _suggestedGroups = MutableStateFlow<List<Group>>(listOf())
	val suggestedGroups = _suggestedGroups.asStateFlow()

	private val _selectedGroup = MutableStateFlow("")
	var selectedGroup = _selectedGroup.asStateFlow()

	fun initialize() {
		updateDatabaseWithRemoteData()
		extractCashedGroupsFromDB()
	}

	fun setRouter(router: StartingGroupMenuRouter) {
		this.router = router
	}

	fun onGroupChange(group: String) {
		_selectedGroup.value = group
	}

	fun onSuggestionItemSelect(group: String) {
		if (_selectedGroup.value != group) {
			onSuggestionItemPerform = true
			_selectedGroup.value = group
		} else {
			_state.value = _state.value.copy(isSuggestionsExpanded = false)
		}
	}

	fun noGroupErrorWasShown() {
		_state.value = StartingGroupMenuScreenState.NoError(_state.value.isSuggestionsExpanded)
	}

	fun onDismissAction() {
		_state.value = _state.value.copy(isSuggestionsExpanded = false)
	}

	fun onSubmitGroupAction() {
		_state.value = StartingGroupMenuScreenState.Loading()
		checkIsGroupExists()
	}

	private fun checkIsGroupExists() {
		viewModelScope.launch {
			if (!isGroupExistUseCase(_selectedGroup.value))
				_state.value = StartingGroupMenuScreenState.NoGroupError(_state.value.isSuggestionsExpanded)
			else {
				updateCurrentGroupInPreferencesUseCase(_selectedGroup.value)
				withContext(Dispatchers.Main) {
					router.navigateToMainBottomNavScreen(_selectedGroup.value)
				}
			}
		}
	}

	private fun updateDatabaseWithRemoteData() {
		viewModelScope.launch {
			try {
				updateLocalGroupStorageUseCase()
			} catch (exception: Exception) {
				Log.d("DT", "${this.javaClass.name} ${exception.message}")
			}
		}
	}

	private fun extractCashedGroupsFromDB() {
		_selectedGroup
			.filter(String::isNotEmpty)
			.debounce(debounceTime)
			.onEach(this::getGroupsWithNewFilter)
			.launchIn(viewModelScope)
	}

	private fun getGroupsWithNewFilter(filter: String) {
		localStorageGroupsChangesJob?.cancel()
		localStorageGroupsChangesJob = getGroupsListUseCase(filter)
			.onEach { newList ->
				_state.value = _state.value.copy(
					isSuggestionsExpanded = newList.isNotEmpty() && !onSuggestionItemPerform
				)

				_suggestedGroups.value = newList
			}
			.launchIn(viewModelScope)
	}

	fun onLeavingComposition() {
		_state.value = _state.value.copy(isSuggestionsExpanded = false)
	}
}