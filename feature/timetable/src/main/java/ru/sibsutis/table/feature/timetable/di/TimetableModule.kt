package ru.sibsutis.table.feature.timetable.di

import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.feature.timetable.data.api.TimetableApi
import ru.sibsutis.table.feature.timetable.data.datasource.TimetableDataSource
import ru.sibsutis.table.feature.timetable.data.datasource.TimetableDataSourceImpl
import ru.sibsutis.table.feature.timetable.data.repository.TimetableRepositoryImpl
import ru.sibsutis.table.feature.timetable.domain.repository.TimetableRepository
import ru.sibsutis.table.feature.timetable.domain.usecases.GetTimetableUseCase
import ru.sibsutis.table.feature.timetable.domain.usecases.UpdateTimetableLocalStorageUseCase

val timetableModule = module {

	factory { createRetrofitService<TimetableApi>(retrofit = getRetrofit(MOCK)) }

	factory<TimetableDataSource> { TimetableDataSourceImpl(api = get(), dao = get()) }

	factory<TimetableRepository> { TimetableRepositoryImpl(dataSource = get()) }

	factory { UpdateTimetableLocalStorageUseCase(repository = get()) }
	factory { GetTimetableUseCase(repository = get()) }
}