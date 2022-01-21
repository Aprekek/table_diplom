package ru.sibsutis.table.features.teachers.listscreen.data.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.sibsutis.table.database.entities.TeacherEntity
import ru.sibsutis.table.features.teachers.listscreen.data.model.TeachersListModel
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher

fun TeachersListModel.toDatabaseEntity() = TeacherEntity(
	name = name,
	phone = phone,
	email = email,
	address = address,
	cathedra = cathedra,
	photo = photo
)

fun List<TeachersListModel>.toDatabaseEntity() = map(TeachersListModel::toDatabaseEntity)

fun TeacherEntity.toEntity() = Teacher(
	name = name,
	phone = phone,
	email = email,
	address = address,
	cathedra = cathedra,
	photo = photo
)

fun List<TeacherEntity>.toEntityList() = map(TeacherEntity::toEntity)

fun Flow<List<TeacherEntity>>.toEntityList() = map(List<TeacherEntity>::toEntityList)