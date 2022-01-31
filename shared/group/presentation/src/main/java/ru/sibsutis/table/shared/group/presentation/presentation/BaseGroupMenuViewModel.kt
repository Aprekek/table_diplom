package ru.sibsutis.table.shared.group.presentation.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sibsutis.table.shared.group.domain.entities.Group
import ru.sibsutis.table.shared.group.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.shared.group.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateLocalGroupStorageUseCase

abstract class BaseGroupMenuViewModel(
	protected val getGroupsListUseCase: GetGroupsListUseCase,
	protected val updateLocalGroupStorageUseCase: UpdateLocalGroupStorageUseCase,
	protected val isGroupExistUseCase: IsGroupExistUseCase,
	protected val updateCurrentGroupInPreferencesUseCase: UpdateCurrentGroupInPreferencesUseCase
) : ViewModel() {

	protected val debounceTime = 200L
	protected var localStorageGroupsChangesJob: Job? = null

	protected var _state =
		MutableStateFlow<GroupMenuScreenState>(GroupMenuScreenState.NoError(isSuggestionsExpanded = true))
	val state = _state.asStateFlow()

	protected var onSuggestionItemPerform = false
		get() {
			val t = field
			field = false
			return t
		}
	protected var _suggestedGroups = MutableStateFlow<List<Group>>(listOf())
	val suggestedGroups = _suggestedGroups.asStateFlow()

	protected val _selectedGroup = MutableStateFlow("")
	var selectedGroup = _selectedGroup.asStateFlow()

	open fun initialize() {
		updateDatabaseWithRemoteData()
		extractCashedGroupsFromDB()
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
		_state.value = GroupMenuScreenState.NoError(_state.value.isSuggestionsExpanded)
	}

	fun onDismissAction() {
		_state.value = _state.value.copy(isSuggestionsExpanded = false)
	}

	open fun onSubmitGroupAction() {
		_state.value = GroupMenuScreenState.Loading()
		checkIsGroupExists()
	}

	protected abstract suspend fun onGroupExistingAction()

	protected fun checkIsGroupExists() {
		viewModelScope.launch {
			if (!isGroupExistUseCase(_selectedGroup.value))
				_state.value = GroupMenuScreenState.NoGroupError(_state.value.isSuggestionsExpanded)
			else {
				updateCurrentGroupInPreferencesUseCase(_selectedGroup.value)
				onGroupExistingAction()
			}
		}
	}

	protected fun updateDatabaseWithRemoteData() {
		viewModelScope.launch {
			try {
				updateLocalGroupStorageUseCase()
			} catch (exception: Exception) {
				Log.d("DT", "${this.javaClass.name} ${exception.message}")
			}
		}
	}

	protected fun extractCashedGroupsFromDB() {
		_selectedGroup
			.filter(String::isNotEmpty)
			.debounce(debounceTime)
			.onEach(this::getGroupsWithNewFilter)
			.launchIn(viewModelScope)
	}

	protected fun getGroupsWithNewFilter(filter: String) {
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