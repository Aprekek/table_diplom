package ru.sibsutis.table.navigation.navigator

import android.os.Parcel
import android.os.Parcelable
import kotlinx.coroutines.flow.MutableStateFlow

class BottomBarNavigator(
	currentScreen: String
) : Parcelable {

	constructor(parcel: Parcel) : this(currentScreen = parcel.readString()!!) {
		parcel.readStringList(backStack)
	}

	private val backStack: MutableList<String> = mutableListOf(currentScreen)

	var currentScreen = MutableStateFlow(currentScreen)

	fun navigateTo(screenId: String) {
		val prevPosition = backStack.indexOf(screenId)

		if (prevPosition == -1) {
			backStack.add(screenId)
			currentScreen.value = screenId
			return
		}

		if (screenId == backStack.last())
			return

		backStack.removeAt(prevPosition)
		backStack.add(screenId)
		currentScreen.value = screenId
	}

	fun popBack(): Boolean {
		return if (backStack.size > 1) {
			backStack.removeLast()
			currentScreen.value = backStack.last()
			true
		} else {
			false
		}
	}

	override fun describeContents() = 0

	override fun writeToParcel(dest: Parcel, flags: Int) {
		dest.writeString(currentScreen.value)
		dest.writeStringList(backStack)
	}

	companion object CREATOR : Parcelable.Creator<BottomBarNavigator> {

		override fun createFromParcel(parcel: Parcel): BottomBarNavigator {
			return BottomBarNavigator(parcel)
		}

		override fun newArray(size: Int): Array<BottomBarNavigator?> {
			return arrayOfNulls(size)
		}
	}
}