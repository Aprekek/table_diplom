package ru.sibsutis.table.feature.timetable.di

import org.koin.dsl.module
import ru.sibsutis.table.feature.timetable.domain.usecases.GetTimetableUseCase
import ru.sibsutis.table.feature.timetable.domain.usecases.UpdateTimetableLocalStorageUseCase

val timetableModule = module {

	factory { UpdateTimetableLocalStorageUseCase(repository = get()) }
	factory { GetTimetableUseCase(repository = get()) }
}