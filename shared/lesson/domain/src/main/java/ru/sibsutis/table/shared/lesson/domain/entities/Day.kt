package ru.sibsutis.table.shared.lesson.domain.entities

enum class Day(val value: Int, val localizedValue: String) {
	Monday(value = 0, localizedValue = "Понедельник"),
	TUESDAY(value = 1, localizedValue = "Вторник"),
	WEDNESDAY(value = 2, localizedValue = "Среда"),
	THURSDAY(value = 3, localizedValue = "Четверг"),
	FRIDAY(value = 4, localizedValue = "Пятница"),
	SATURDAY(value = 5, localizedValue = "Суббота"),
	SUNDAY(value = 6, localizedValue = "Воскресенье")
}