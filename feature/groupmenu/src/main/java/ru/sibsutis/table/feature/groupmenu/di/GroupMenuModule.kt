package ru.sibsutis.table.feature.groupmenu.di

import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.feature.groupmenu.data.api.GroupsMenuApi
import ru.sibsutis.table.feature.groupmenu.data.datasource.GroupDatasource
import ru.sibsutis.table.feature.groupmenu.data.datasource.GroupDatasourceImpl
import ru.sibsutis.table.feature.groupmenu.data.repository.GroupMenuRepositoryImpl
import ru.sibsutis.table.feature.groupmenu.domain.repository.GroupMenuRepository

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
}