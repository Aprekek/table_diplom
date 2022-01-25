package ru.sibsutis.table.feature.teachers.detailsscreen.data.mapper

import ru.sibsutis.table.database.entities.TeacherEntity
import ru.sibsutis.table.feature.teachers.detailsscreen.data.model.TeacherModel
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

fun TeacherModel.toEntity() = Teacher(
	name = name,
	phone = phone,
	email = email,
	address = address,
	cathedra = cathedra,
	photo = photo
)

fun TeacherEntity.toModel() = TeacherModel(
	name = name,
	phone = phone,
	email = email,
	address = address,
	cathedra = cathedra,
	photo = photo
)