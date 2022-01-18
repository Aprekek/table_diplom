package ru.sibsutis.table.shared.ui.domain

import androidx.annotation.DrawableRes

data class BottomNavigationItemEntity(
	val id: Int,
	@DrawableRes val image: Int,
	val title: String
)