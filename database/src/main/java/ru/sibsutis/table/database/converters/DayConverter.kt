package ru.sibsutis.table.database.converters

import androidx.room.TypeConverter
import ru.sibsutis.table.shared.lesson.domain.Day

class DayConverter {

	@TypeConverter
	fun dayToInt(day: Day): Int = day.value

	@TypeConverter
	fun intToDay(value: Int): Day = Day.values()[value]
}