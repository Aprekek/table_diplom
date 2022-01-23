package ru.sibsutis.table.feature.groups.startingscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.table.feature.groups.startingscreen.presentation.StartingGroupMenuScreenViewModel

val groupMenuModule = module {

	viewModel {
		StartingGroupMenuScreenViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get()
		).apply { initialize() }
	}
}