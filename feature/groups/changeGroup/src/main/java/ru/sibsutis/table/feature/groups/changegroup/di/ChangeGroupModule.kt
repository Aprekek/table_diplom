package ru.sibsutis.table.feature.groups.changegroup.di

import org.koin.dsl.module
import ru.sibsutis.table.feature.groups.changegroup.domain.usecase.AddGroupToRecentlyWatchedUseCase
import ru.sibsutis.table.feature.groups.changegroup.domain.usecase.GetRecentlyWatchedGroupsUseCase

val changeGroupModule = module {

	factory { GetRecentlyWatchedGroupsUseCase(repository = get()) }
	factory { AddGroupToRecentlyWatchedUseCase(repository = get()) }
}