package ru.sibsutis.table.feature.groups.changegroup.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.table.feature.groups.changegroup.data.datasource.RecentlyWatchedGroupsDataSource
import ru.sibsutis.table.feature.groups.changegroup.data.datasource.RecentlyWatchedGroupsDataSourceImpl
import ru.sibsutis.table.feature.groups.changegroup.data.repository.RecentlyWatchedGroupsRepositoryImpl
import ru.sibsutis.table.shared.group.domain.repository.RecentlyWatchedGroupsRepository
import ru.sibsutis.table.shared.group.domain.usecases.AddGroupToRecentlyWatchedUseCase
import ru.sibsutis.table.shared.group.domain.usecases.GetRecentlyWatchedGroupsUseCase
import ru.sibsutis.table.feature.groups.changegroup.presentation.ChangeGroupViewModel

val changeGroupModule = module {

	factory<RecentlyWatchedGroupsDataSource> { RecentlyWatchedGroupsDataSourceImpl(dao = get()) }

	factory<RecentlyWatchedGroupsRepository> { RecentlyWatchedGroupsRepositoryImpl(dataSource = get()) }

	viewModel { (currentlyActiveGroup: String) ->
		ChangeGroupViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get(),
			getRecentlyWatchedGroupsUseCase = get(),
			addGroupToRecentlyWatchedUseCase = get(),
			currentlyActiveGroup = currentlyActiveGroup
		).apply { initialize() }
	}
}