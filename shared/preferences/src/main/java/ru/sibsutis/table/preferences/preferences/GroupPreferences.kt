package ru.sibsutis.table.preferences.preferences

import android.content.SharedPreferences

class GroupPreferences(
	private val sharedPreferences: SharedPreferences
) {

	private companion object {

		const val GROUP_KEY = "group_key"
	}

	fun saveGroup(groupName: String) {
		sharedPreferences.edit().putString(GROUP_KEY, groupName).apply()
	}

	fun getGroup() = sharedPreferences.getString(GROUP_KEY, null)
}