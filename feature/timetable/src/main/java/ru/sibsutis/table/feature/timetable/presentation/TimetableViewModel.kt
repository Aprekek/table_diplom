package ru.sibsutis.table.feature.timetable.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sibsutis.table.feature.timetable.domain.entities.Lesson
import ru.sibsutis.table.feature.timetable.domain.usecases.GetTimetableUseCase
import ru.sibsutis.table.feature.timetable.domain.usecases.UpdateTimetableLocalStorageUseCase
import ru.sibsutis.table.shared.core.DateUtils
import ru.sibsutis.table.shared.lesson.domain.entities.WeekType

class TimetableViewModel(
	private val group: String,
	private val getTimetableUseCase: GetTimetableUseCase,
	private val updateTimetableLocalStorageUseCase: UpdateTimetableLocalStorageUseCase,
) : ViewModel() {

	companion object {

		const val TOTAL_WEEKS = 2
		const val TOTAL_DAYS = 7
	}

	private var _state = MutableStateFlow<TimetableState>(TimetableState.Initial)
	val state = _state.asStateFlow()

	private var visitedWeeks = 0
	val currentWeek = DateUtils.getCurrentWeek()
	private var _week = MutableStateFlow(currentWeek)
	val week = _week.asStateFlow()

	val currentDay = DateUtils.getCurrentDay()
	private var _day = MutableStateFlow(currentDay)
	val day = _day.asStateFlow()

	private val firstWeekDates by lazy { DateUtils.getWeekDates(WeekType.FIRST.value) }
	private val secondWeekDates by lazy { DateUtils.getWeekDates(WeekType.SECOND.value) }

	fun getDates(): List<String> {
		return if (week.value == WeekType.FIRST.value)
			firstWeekDates
		else
			secondWeekDates
	}

	private var firsWeekLessons: Map<Int, List<Lesson>> = mapOf()
	private var secondWeekLessons: Map<Int, List<Lesson>> = mapOf()

	fun initialize() {
		viewModelScope.launch {
			_state.value = TimetableState.Loading

			val preLoadedLessons = getTimetableUseCase(WeekType.values()[week.value])
			if (preLoadedLessons.isEmpty()) {
				updateLocalStorage()
				initWeekLessonsObservation()
			} else {
				initWeekLessonsObservation(preLoadedLessons)
			}
		}
	}

	fun onWeekSelect(week: Int) {
		_week.value = week
	}

	fun onDaySelect(day: Int) {
		_day.value = day
	}

	fun setCurrentDays() {
		_week.value = currentWeek
		_day.value = currentDay
	}

	private suspend fun updateLocalStorage() {
		try {
			updateTimetableLocalStorageUseCase(group)
		} catch (e: Exception) {
			Log.d("DT", "${this.javaClass.name} F:updateLocalStorage M:${e.message}")
		}
	}

	private fun initWeekLessonsObservation(preLoadedLessons: Map<Int, List<Lesson>>? = null) {
		week.onEach {
			if (visitedWeeks < TOTAL_WEEKS) {
				visitedWeeks++
				loadWeekTimetable(week = it, preLoadedLessons = preLoadedLessons)
			} else {
				if (state.value is TimetableState.Content)
					setLessonsToContent(it)
			}
		}.launchIn(viewModelScope)
	}

	private fun setLessonsToContent(week: Int) {
		_state.value = TimetableState.Content(lessons = getActualWeekLessons(week))
	}

	private fun getActualWeekLessons(week: Int) = if (week == WeekType.FIRST.value) firsWeekLessons else secondWeekLessons

	private suspend fun loadWeekTimetable(
		week: Int,
		preLoadedLessons: Map<Int, List<Lesson>>? = null
	) {
		_state.value = TimetableState.Loading

		val lessons: Map<Int, List<Lesson>>
		try {
			if (week == WeekType.FIRST.value) {
				firsWeekLessons = preLoadedLessons ?: getTimetableUseCase(WeekType.FIRST)
				lessons = firsWeekLessons
			} else {
				secondWeekLessons = preLoadedLessons ?: getTimetableUseCase(WeekType.SECOND)
				lessons = secondWeekLessons
			}
			_state.value = TimetableState.Content(lessons)
		} catch (e: Exception) {
			Log.d("DT", "${this.javaClass.name} F:loadWeekTimetable M:${e.message}")
			_state.value = TimetableState.Error
		}
	}
}