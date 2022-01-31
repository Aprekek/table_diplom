package ru.sibsutis.table.feature.timetable.presentation

import ru.sibsutis.table.feature.timetable.domain.entities.Lesson

sealed class TimetableState {

	object Initial : TimetableState()

	object Loading : TimetableState()

	class Content(val lessons: Map<Int, List<Lesson>>) : TimetableState()

	object Error : TimetableState()
}