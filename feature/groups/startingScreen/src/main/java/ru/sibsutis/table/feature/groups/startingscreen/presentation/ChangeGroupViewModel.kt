package ru.sibsutis.table.feature.groups.startingscreen.presentation

import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.UpdateLocalGroupStorageUseCase

class ChangeGroupViewModel(
	getGroupsListUseCase: GetGroupsListUseCase,
	updateLocalGroupStorageUseCase: UpdateLocalGroupStorageUseCase,
	isGroupExistUseCase: IsGroupExistUseCase,
	updateCurrentGroupInPreferencesUseCase: UpdateCurrentGroupInPreferencesUseCase
) : StartingGroupMenuScreenViewModel(
	getGroupsListUseCase,
	updateLocalGroupStorageUseCase,
	isGroupExistUseCase,
	updateCurrentGroupInPreferencesUseCase
) {

}