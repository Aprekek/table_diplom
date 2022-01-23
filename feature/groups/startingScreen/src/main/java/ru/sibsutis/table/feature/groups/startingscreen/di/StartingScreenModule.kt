package ru.sibsutis.table.feature.groups.startingscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.table.feature.groups.startingscreen.presentation.StartingScreenViewModel

val startingScreenModule = module {
	viewModel {
		StartingScreenViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get()
		).apply { initialize() }
	}
}