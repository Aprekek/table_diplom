package ru.sibsutis.table.feature.groups.changegroup.di

import org.koin.dsl.module
import ru.sibsutis.table.feature.groups.changegroup.data.datasource.RecentlyWatchedGroupsDataSource
import ru.sibsutis.table.feature.groups.changegroup.data.datasource.RecentlyWatchedGroupsDataSourceImpl
import ru.sibsutis.table.feature.groups.changegroup.data.repository.RecentlyWatchedGroupsRepositoryImpl
import ru.sibsutis.table.feature.groups.changegroup.domain.repository.RecentlyWatchedGroupsRepository
import ru.sibsutis.table.feature.groups.changegroup.domain.usecase.AddGroupToRecentlyWatchedUseCase
import ru.sibsutis.table.feature.groups.changegroup.domain.usecase.GetRecentlyWatchedGroupsUseCase

val changeGroupModule = module {

	factory<RecentlyWatchedGroupsDataSource> { RecentlyWatchedGroupsDataSourceImpl(dao = get()) }

	factory<RecentlyWatchedGroupsRepository> { RecentlyWatchedGroupsRepositoryImpl(dataSource = get()) }

	factory { GetRecentlyWatchedGroupsUseCase(repository = get()) }
	factory { AddGroupToRecentlyWatchedUseCase(repository = get()) }
}