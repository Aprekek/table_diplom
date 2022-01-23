package ru.sibsutis.table.feature.groups.changegroup.data.mappers

import ru.sibsutis.table.database.entities.RecentlyWatchedGroupEntity
import ru.sibsutis.table.feature.groups.changegroup.domain.entities.RecentlyWatchedGroup

fun RecentlyWatchedGroupEntity.toEntity() = RecentlyWatchedGroup(id, groupName)
fun RecentlyWatchedGroup.toDatabaseEntity() = RecentlyWatchedGroupEntity(id, groupName)