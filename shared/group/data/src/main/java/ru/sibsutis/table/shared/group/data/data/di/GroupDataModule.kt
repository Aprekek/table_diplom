package ru.sibsutis.table.shared.group.data.data.di

import org.koin.dsl.module
import ru.sibsutis.mockapiserver.changer.MOCK
import ru.sibsutis.mockapiserver.changer.getRetrofit
import ru.sibsutis.network.retrofit.createRetrofitService
import ru.sibsutis.table.shared.group.data.data.api.GroupsMenuApi
import ru.sibsutis.table.shared.group.data.data.datasource.GroupDatasource
import ru.sibsutis.table.shared.group.data.data.datasource.GroupDatasourceImpl
import ru.sibsutis.table.shared.group.data.data.datasource.RecentlyWatchedGroupsDataSource
import ru.sibsutis.table.shared.group.data.data.datasource.RecentlyWatchedGroupsDataSourceImpl
import ru.sibsutis.table.shared.group.data.data.repository.GroupMenuRepositoryImpl
import ru.sibsutis.table.shared.group.data.data.repository.RecentlyWatchedGroupsRepositoryImpl
import ru.sibsutis.table.shared.group.domain.repository.GroupMenuRepository
import ru.sibsutis.table.shared.group.domain.repository.RecentlyWatchedGroupsRepository

val groupDataModule = module {

	factory { createRetrofitService<GroupsMenuApi>(getRetrofit(MOCK)) }

	factory<GroupDatasource> {
		GroupDatasourceImpl(
			dao = get(),
			api = get()
		)
	}
	factory<RecentlyWatchedGroupsDataSource> { RecentlyWatchedGroupsDataSourceImpl(dao = get()) }

	factory<GroupMenuRepository> {
		GroupMenuRepositoryImpl(
			datasource = get()
		)
	}
	factory<RecentlyWatchedGroupsRepository> { RecentlyWatchedGroupsRepositoryImpl(dataSource = get()) }
}