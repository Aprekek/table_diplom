package ru.sibsutis.table.database.converters

import androidx.room.TypeConverter
import ru.sibsutis.table.shared.lesson.WeekType

class WeekTypeConverter {

	@TypeConverter
	operator fun invoke(weekType: WeekType): Int = weekType.value
}