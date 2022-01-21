package ru.sibsutis.table.shared.ui.domain

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class BottomNavigationItemEntity(
	val route: String,
	@DrawableRes val image: Int,
	val title: String
) : Parcelable