package ru.sibsutis.table.feature.groups.changegroup.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.sibsutis.table.navigation.screens.changegroup.ChangeGroupNavigationRouter
import ru.sibsutis.table.shared.group.domain.usecases.AddGroupToRecentlyWatchedUseCase
import ru.sibsutis.table.shared.group.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.shared.group.domain.usecases.GetRecentlyWatchedGroupsUseCase
import ru.sibsutis.table.shared.group.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateLocalGroupStorageUseCase
import ru.sibsutis.table.shared.group.presentation.presentation.BaseGroupMenuViewModel
import ru.sibsutis.table.shared.lesson.domain.usecases.CleanLessonsStorageUseCase

class ChangeGroupViewModel(
	getGroupsListUseCase: GetGroupsListUseCase,
	updateLocalGroupStorageUseCase: UpdateLocalGroupStorageUseCase,
	isGroupExistUseCase: IsGroupExistUseCase,
	updateCurrentGroupInPreferencesUseCase: UpdateCurrentGroupInPreferencesUseCase,
	private val getRecentlyWatchedGroupsUseCase: GetRecentlyWatchedGroupsUseCase,
	private val addGroupToRecentlyWatchedUseCase: AddGroupToRecentlyWatchedUseCase,
	private val cleanLessonsStorageUseCase: CleanLessonsStorageUseCase,
	private val currentlyActiveGroup: String
) : BaseGroupMenuViewModel(
	getGroupsListUseCase,
	updateLocalGroupStorageUseCase,
	isGroupExistUseCase,
	updateCurrentGroupInPreferencesUseCase
) {

	private lateinit var router: ChangeGroupNavigationRouter

	private val _recentlyWatchedGroups = MutableStateFlow<List<String>>(listOf())
	val recentlyWatchedGroups = _recentlyWatchedGroups.asStateFlow()

	override fun initialize() {
		super.initialize()
		getRecentlyWatchedGroups()
	}

	fun setRouter(router: ChangeGroupNavigationRouter) {
		this.router = router
	}

	override fun onSubmitGroupAction() {
		if (_selectedGroup.value != currentlyActiveGroup) {
			super.onSubmitGroupAction()
		} else {
			router.popToMainBottomNavigation()
		}
	}

	override suspend fun onGroupExistingAction() {
		coroutineScope {
			launch {
				addGroupToRecentlyWatchedUseCase(_selectedGroup.value)
				Log.d("DT","recently added")
			}
			launch {
				cleanLessonsStorageUseCase()
				Log.d("DT","cleaned")
			}
		}
		withContext(Dispatchers.Main) {
			router.navigateToMainBottomNavigation(_selectedGroup.value)
		}
	}

	private fun getRecentlyWatchedGroups() {
		viewModelScope.launch {
			_recentlyWatchedGroups.value = getRecentlyWatchedGroupsUseCase(exceptGroup = currentlyActiveGroup)
		}
	}

	fun onRecentlyWatchedGroupSelect(selectedGroup: String) {
		viewModelScope.launch {
			launch {
				addGroupToRecentlyWatchedUseCase(selectedGroup)
			}
			launch {
				_selectedGroup.value = selectedGroup
				super.onSubmitGroupAction()
			}
		}
	}
}