package ru.sibsutis.table

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import kotlinx.coroutines.FlowPreview
import ru.sibsutis.table.feature.groupmenu.ui.GroupMenuScreen
import ru.sibsutis.table.navigation.GlobalController

@FlowPreview
@ExperimentalMaterialApi
class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			GlobalController(startDestination = GroupMenuScreen.ROUTE)
		}
	}
}