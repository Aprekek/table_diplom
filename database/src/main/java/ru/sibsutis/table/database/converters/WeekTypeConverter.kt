package ru.sibsutis.table.database.converters

import androidx.room.TypeConverter
import ru.sibsutis.table.shared.lesson.WeekType

class WeekTypeConverter {

	@TypeConverter
	fun weekTypeToInt(weekType: WeekType): Int = weekType.value

	@TypeConverter
	fun intToWeekType(value: Int): WeekType = WeekType.values()[value]
}