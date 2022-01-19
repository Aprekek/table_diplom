package ru.sibsutis.table.feature.groupmenu.data.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sibsutis.table.database.entities.GroupEntity
import ru.sibsutis.table.feature.groupmenu.data.model.GroupModel
import ru.sibsutis.table.feature.groupmenu.domain.entities.Group

fun GroupModel.toDatabaseEntity() = GroupEntity(name = name)
fun List<GroupModel>.toDatabaseEntity() = map(GroupModel::toDatabaseEntity)

fun GroupEntity.toEntity() = Group(name = name)
fun List<GroupEntity>.toEntityList() = map(GroupEntity::toEntity)
fun Flow<List<GroupEntity>>.toEntityList() = map(List<GroupEntity>::toEntityList)

fun List<Group>.getNames() = map { it.name }