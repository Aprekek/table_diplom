package ru.sibsutis.table.shared.domain.data.di

import org.koin.dsl.module
import ru.sibsutis.table.shared.domain.data.datasource.SharedLessonDataSource
import ru.sibsutis.table.shared.domain.data.datasource.SharedLessonDataSourceImpl
import ru.sibsutis.table.shared.domain.data.repository.SharedLessonsRepositoryImpl
import ru.sibsutis.table.shared.lesson.domain.repository.SharedLessonsRepository

val lessonsDataModule = module {

	factory<SharedLessonDataSource> { SharedLessonDataSourceImpl(dao = get()) }

	factory<SharedLessonsRepository> { SharedLessonsRepositoryImpl(dataSource = get()) }
}