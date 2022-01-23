package ru.sibsutis.table.feature.groups.startingscreen.domain.usecases

import ru.sibsutis.table.preferences.preferences.GroupPreferences

class UpdateCurrentGroupInPreferencesUseCase(
	private val groupPreferences: GroupPreferences
) {

	operator fun invoke(group: String) {
		groupPreferences.saveGroup(groupName = group)
	}
}