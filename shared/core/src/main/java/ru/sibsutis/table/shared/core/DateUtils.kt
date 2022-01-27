package ru.sibsutis.table.shared.core

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

	const val DAYS_IN_WEEK = 7

	const val WEEK_TYPES = 2
	const val FIRST_WEEK = 0
	const val SECOND_WEEK = 1

	private const val MILLISECONDS_IN_DAY = 86400000
	private const val CALENDAR_DAY_DIFF = 2
	private const val SUNDAY = 6

	fun getWeekDates(weekType: Int): List<String> {
		val calendar = Calendar.getInstance()
		if (weekType != getCurrentWeek()) {
			calendar.add(Calendar.WEEK_OF_YEAR, 1)
		}

		val dateFormatter = SimpleDateFormat("dd.MM.yy", Locale("rus"))

		val dates = mutableListOf<String>()
		for (day in Calendar.SUNDAY..Calendar.SATURDAY) {
			calendar.set(Calendar.DAY_OF_WEEK, day)
			dates.add(dateFormatter.format(calendar.time))
		}
		return dates.apply {
			add(first())
			removeFirst()
		}
	}

	fun getCurrentWeek(): Int {
		val calendar = Calendar.getInstance()

		if (calendar.get(Calendar.MONTH) < Calendar.SEPTEMBER) {
			calendar.add(Calendar.YEAR, -1)
		}
		calendar.set(Calendar.MONTH, Calendar.SEPTEMBER)
		calendar.set(Calendar.DAY_OF_MONTH, 1)
		if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			calendar.set(Calendar.DAY_OF_MONTH, 2)
		}

		val firstDay = calendar.time
		val today = Date()
		val daysDiff = (today.time - firstDay.time) / MILLISECONDS_IN_DAY
		val weeksDiff = (daysDiff + calendar.get(Calendar.DAY_OF_WEEK) - CALENDAR_DAY_DIFF) / DAYS_IN_WEEK

		return if ((weeksDiff % WEEK_TYPES).toInt() == FIRST_WEEK) FIRST_WEEK else SECOND_WEEK
	}

	fun getCurrentDay(): Int {
		val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
		return if (currentDay == Calendar.SUNDAY) SUNDAY else currentDay - CALENDAR_DAY_DIFF
	}
}