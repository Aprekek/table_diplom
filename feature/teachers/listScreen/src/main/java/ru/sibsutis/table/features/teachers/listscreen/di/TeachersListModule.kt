package ru.sibsutis.table.features.teachers.listscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.features.teachers.listscreen.data.api.TeachersListApi
import ru.sibsutis.table.features.teachers.listscreen.data.datasource.TeachersListDatasource
import ru.sibsutis.table.features.teachers.listscreen.data.datasource.TeachersListDatasourceImpl
import ru.sibsutis.table.features.teachers.listscreen.data.repository.TeachersListRepositoryImpl
import ru.sibsutis.table.features.teachers.listscreen.domain.repository.TeachersListRepository
import ru.sibsutis.table.features.teachers.listscreen.domain.usecase.GetTeachersListUseCase
import ru.sibsutis.table.features.teachers.listscreen.domain.usecase.UpdateLocalTeachersStorageUseCase
import ru.sibsutis.table.features.teachers.listscreen.presentation.TeachersListViewModel

val teachersListScreenModule = module {

	factory { createRetrofitService<TeachersListApi>(getRetrofit(MOCK)) }

	factory<TeachersListDatasource> {
		TeachersListDatasourceImpl(
			dao = get(),
			api = get()
		)
	}

	factory<TeachersListRepository> {
		TeachersListRepositoryImpl(
			datasource = get(),
			dao = get()
		)
	}

	factory { GetTeachersListUseCase(repository = get()) }
	factory { UpdateLocalTeachersStorageUseCase(repository = get()) }

	viewModel {
		TeachersListViewModel(
			updateLocalTeachersStorageUseCase = get(),
			getTeachersListUseCase = get()
		).apply { initialize() }
	}
}