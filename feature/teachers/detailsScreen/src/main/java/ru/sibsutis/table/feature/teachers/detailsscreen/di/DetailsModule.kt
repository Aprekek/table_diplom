package ru.sibsutis.table.feature.teachers.detailsscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource.DetailsDataSource
import ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource.DetailsDataSourceImpl
import ru.sibsutis.table.feature.teachers.detailsscreen.data.repository.DetailsRepositoryImpl
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository.DetailsRepository
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.usecase.GetTeacherUseCase
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.DetailsViewModel

val detailsModule = module {

	factory<DetailsDataSource> { DetailsDataSourceImpl(dao = get()) }

	factory<DetailsRepository> {
		DetailsRepositoryImpl(dataSource = get())
	}

	factory { GetTeacherUseCase(repository = get()) }

	viewModel { (name: String) ->
		DetailsViewModel(
			getTeacherUseCase = get(), name = name
		).apply {
			loadInfo()
		}
	}
}