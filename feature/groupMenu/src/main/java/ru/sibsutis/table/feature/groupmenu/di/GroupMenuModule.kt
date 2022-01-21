package ru.sibsutis.table.feature.groupmenu.di

import androidx.navigation.NavController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.feature.groupmenu.GroupMenuRouter
import ru.sibsutis.table.feature.groupmenu.data.api.GroupsMenuApi
import ru.sibsutis.table.feature.groupmenu.data.datasource.GroupDatasource
import ru.sibsutis.table.feature.groupmenu.data.datasource.GroupDatasourceImpl
import ru.sibsutis.table.feature.groupmenu.data.repository.GroupMenuRepositoryImpl
import ru.sibsutis.table.feature.groupmenu.domain.repository.GroupMenuRepository
import ru.sibsutis.table.feature.groupmenu.domain.usecases.GetGroupsListUseCase
import ru.sibsutis.table.feature.groupmenu.domain.usecases.IsGroupExistUseCase
import ru.sibsutis.table.feature.groupmenu.domain.usecases.UpdateCurrentGroupInPreferencesUseCase
import ru.sibsutis.table.feature.groupmenu.domain.usecases.UpdateLocalGroupStorageUseCase
import ru.sibsutis.table.feature.groupmenu.presentation.StarterScreenViewModel

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

	factory { (navController: NavController) -> GroupMenuRouter(navController) }

	viewModel {
		StarterScreenViewModel(
			getGroupsListUseCase = get(),
			updateLocalGroupStorageUseCase = get(),
			isGroupExistUseCase = get(),
			updateCurrentGroupInPreferencesUseCase = get()
		).apply { initialize() }
	}
}