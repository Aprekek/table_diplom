package ru.sibsutis.table.database.converters

import androidx.room.TypeConverter
import ru.sibsutis.table.shared.lesson.LessonType

class LessonTypeConverter {

	@TypeConverter
	operator fun invoke(lessonType: LessonType): Int = lessonType.value
}