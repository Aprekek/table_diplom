package ru.sibsutis.table.feature.groups.startingscreen.presentation

import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuRouter
import ru.sibsutis.table.shared.group.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.shared.group.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateLocalGroupStorageUseCase
import ru.sibsutis.table.shared.group.presentation.presentation.BaseGroupMenuViewModel

class StartingScreenViewModel(
	getGroupsListUseCase: GetGroupsListUseCase,
	updateLocalGroupStorageUseCase: UpdateLocalGroupStorageUseCase,
	isGroupExistUseCase: IsGroupExistUseCase,
	updateCurrentGroupInPreferencesUseCase: UpdateCurrentGroupInPreferencesUseCase
) : BaseGroupMenuViewModel(
	getGroupsListUseCase,
	updateLocalGroupStorageUseCase,
	isGroupExistUseCase,
	updateCurrentGroupInPreferencesUseCase
) {

	private lateinit var router: StartingGroupMenuRouter

	fun setRouter(router: StartingGroupMenuRouter) {
		this.router = router
	}

	override fun onGroupExistingAction() {
		router.navigateToMainBottomNavScreen(_selectedGroup.value)
	}
}