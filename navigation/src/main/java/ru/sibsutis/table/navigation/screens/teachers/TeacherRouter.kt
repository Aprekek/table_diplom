package ru.sibsutis.table.navigation.screens.teachers

import androidx.navigation.NavController

class TeacherRouter(
	private val navigator: NavController
) {

	fun navigateToDetails(currentTeacher: String) {
		navigator.navigate(
			TeachersDetailContent.createPath(currentTeacher)
		)
	}
}