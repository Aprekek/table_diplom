package ru.sibsutis.table.shared.group.domain.di

import org.koin.dsl.module
import ru.sibsutis.table.shared.group.domain.usecases.AddGroupToRecentlyWatchedUseCase
import ru.sibsutis.table.shared.group.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.shared.group.domain.usecases.GetRecentlyWatchedGroupsUseCase
import ru.sibsutis.table.shared.group.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.shared.group.domain.usecases.UpdateLocalGroupStorageUseCase

val groupDomainModule = module {

	factory { GetGroupsListUseCase(repository = get()) }
	factory { UpdateLocalGroupStorageUseCase(repository = get()) }
	factory { IsGroupExistUseCase(repository = get()) }
	factory { UpdateCurrentGroupInPreferencesUseCase(groupPreferences = get()) }
	factory { GetRecentlyWatchedGroupsUseCase(repository = get()) }
	factory { AddGroupToRecentlyWatchedUseCase(repository = get()) }
}