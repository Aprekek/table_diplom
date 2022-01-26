package ru.sibsutis.table.database.converters

import androidx.room.TypeConverter
import ru.sibsutis.table.shared.lesson.LessonType

class LessonTypeConverter {

	@TypeConverter
	fun lessonToInt(lessonType: LessonType): Int = lessonType.value

	@TypeConverter
	fun intToLesson(value: Int): LessonType = LessonType.values()[value]
}