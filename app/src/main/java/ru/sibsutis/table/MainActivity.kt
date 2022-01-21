package ru.sibsutis.table

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import ru.sibsutis.table.feature.groupmenu.ui.StartingGroupMenuScreen
import ru.sibsutis.table.navigation.GlobalController
import ru.sibsutis.table.navigation.screens.startinggroupmenu.StartingGroupMenuContent

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			GlobalController(startDestination = StartingGroupMenuContent.path)
		}
	}
}