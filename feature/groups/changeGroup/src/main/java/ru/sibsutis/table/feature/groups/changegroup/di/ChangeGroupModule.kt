package ru.sibsutis.table.feature.groups.changegroup.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.table.feature.groups.changegroup.presentation.ChangeGroupViewModel

val changeGroupModule = module {

	viewModel { (currentlyActiveGroup: String) ->
		ChangeGroupViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get(),
			getRecentlyWatchedGroupsUseCase = get(),
			addGroupToRecentlyWatchedUseCase = get(),
			cleanLessonsStorageUseCase = get(),
			currentlyActiveGroup = currentlyActiveGroup
		).apply { initialize() }
	}
}