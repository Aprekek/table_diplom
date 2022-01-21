package ru.sibsutis.table.shared.ui.domain

import androidx.annotation.DrawableRes

data class BottomNavigationItemEntity(
	val route: String,
	@DrawableRes val image: Int,
	val title: String
)