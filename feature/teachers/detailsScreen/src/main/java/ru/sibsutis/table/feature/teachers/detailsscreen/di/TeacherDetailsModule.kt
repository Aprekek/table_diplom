package ru.sibsutis.table.feature.teachers.detailsscreen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource.TeacherDetailsDataSource
import ru.sibsutis.table.feature.teachers.detailsscreen.data.datasource.TeacherDetailsDataSourceImpl
import ru.sibsutis.table.feature.teachers.detailsscreen.data.repository.TeacherDetailsRepositoryImpl
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.repository.TeacherDetailsRepository
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.usecase.GetTeacherUseCase
import ru.sibsutis.table.feature.teachers.detailsscreen.presentation.TeacherDetailsViewModel

val teacherDetailsModule = module {

	factory<TeacherDetailsDataSource> { TeacherDetailsDataSourceImpl(dao = get()) }

	factory<TeacherDetailsRepository> {
		TeacherDetailsRepositoryImpl(dataSource = get())
	}

	factory { GetTeacherUseCase(repository = get()) }

	viewModel { (name: String) ->
		TeacherDetailsViewModel(
			getTeacherUseCase = get(),
			name = name
		).apply { loadInfo() }
	}
}