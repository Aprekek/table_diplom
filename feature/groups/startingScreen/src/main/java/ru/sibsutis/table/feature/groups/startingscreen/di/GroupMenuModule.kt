package ru.sibsutis.table.feature.groups.startingscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.feature.groups.startingscreen.presentation.StartingGroupMenuScreenViewModel
import ru.sibsutis.table.shared.group.data.data.api.GroupsMenuApi
import ru.sibsutis.table.shared.group.data.data.datasource.GroupDatasource
import ru.sibsutis.table.shared.group.data.data.datasource.GroupDatasourceImpl
import ru.sibsutis.table.shared.group.data.data.repository.GroupMenuRepositoryImpl
import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository

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

	viewModel {
		StartingGroupMenuScreenViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get()
		).apply { initialize() }
	}
}