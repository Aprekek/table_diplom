package ru.sibsutis.table.shared.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.sibsutis.table.shared.ui.domain.BottomNavigationItemEntity

@Composable
fun BottomNavigationDt(
	selectedItemId: Int,
	items: List<BottomNavigationItemEntity>,
	onItemClick: (BottomNavigationItemEntity) -> Unit
) {

	BottomNavigation {
		items.forEach { item ->
			BottomNavigationItem(
				selected = selectedItemId == item.id,
				onClick = { onItemClick(item) },
				icon = { Icon(painter = painterResource(id = item.image), contentDescription = null) },
				label = { Text(text = item.title) },
			)
		}
	}
}