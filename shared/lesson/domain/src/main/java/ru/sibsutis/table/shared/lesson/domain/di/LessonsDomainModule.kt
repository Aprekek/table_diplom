package ru.sibsutis.table.shared.lesson.domain.di

import org.koin.dsl.module
import ru.sibsutis.table.shared.lesson.domain.usecases.CleanLessonsStorageUseCase

val lessonsDomainModule = module {

	factory { CleanLessonsStorageUseCase(repository = get()) }
}