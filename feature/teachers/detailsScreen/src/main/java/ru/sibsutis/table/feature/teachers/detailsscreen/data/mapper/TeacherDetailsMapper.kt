package ru.sibsutis.table.feature.teachers.detailsscreen.data.mapper

import ru.sibsutis.table.database.entities.TeacherEntity
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.entity.Teacher

fun TeacherEntity.toEntity() = Teacher(
	name = name,
	phone = phone,
	email = email,
	address = address,
	cathedra = cathedra,
	photo = photo
)