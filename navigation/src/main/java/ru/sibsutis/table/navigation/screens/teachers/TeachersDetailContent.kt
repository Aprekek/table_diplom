package ru.sibsutis.table.navigation.screens.teachers

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import ru.sibsutis.table.navigation.BaseNavInfo

interface TeachersDetailContent {

	class Arguments {
		companion object {

			const val TEACHER_NAME = "teacherName"
		}
	}

	companion object : BaseNavInfo {

		override val path = "teachers/{${Arguments.TEACHER_NAME}}"

		fun createPath(teacherName:String): String{
			return "teachers/${teacherName}"
		}
	}

	fun route(navGraphBuilder: NavGraphBuilder, navController: NavController, teacherName: String)
}