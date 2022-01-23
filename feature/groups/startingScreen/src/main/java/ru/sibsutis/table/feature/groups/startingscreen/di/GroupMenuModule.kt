package ru.sibsutis.table.feature.groups.startingscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.feature.groups.startingscreen.data.api.GroupsMenuApi
import ru.sibsutis.table.feature.groups.startingscreen.data.datasource.GroupDatasource
import ru.sibsutis.table.feature.groups.startingscreen.data.datasource.GroupDatasourceImpl
import ru.sibsutis.table.feature.groups.startingscreen.data.repository.GroupMenuRepositoryImpl
import ru.sibsutis.table.feature.groups.startingscreen.domain.repository.GroupMenuRepository
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.feature.groups.startingscreen.domain.usecases.UpdateLocalGroupStorageUseCase
import ru.sibsutis.table.feature.groups.startingscreen.presentation.StartingGroupMenuScreenViewModel

val groupMenuModule = module {

	factory { createRetrofitService<GroupsMenuApi>(getRetrofit(MOCK)) }

	factory<GroupDatasource> {
		GroupDatasourceImpl(
			dao = get(),
			api = get()
		)
	}

	factory<GroupMenuRepository> {
		GroupMenuRepositoryImpl(
			datasource = get(),
			dao = get()
		)
	}

	factory { GetGroupsListUseCase(repository = get()) }
	factory { UpdateLocalGroupStorageUseCase(repository = get()) }
	factory { IsGroupExistUseCase(repository = get()) }
	factory { UpdateCurrentGroupInPreferencesUseCase(groupPreferences = get()) }

	viewModel {
		StartingGroupMenuScreenViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get()
		).apply { initialize() }
	}
}