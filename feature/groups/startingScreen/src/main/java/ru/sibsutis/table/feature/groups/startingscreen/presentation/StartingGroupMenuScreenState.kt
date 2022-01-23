package ru.sibsutis.table.feature.groups.startingscreen.presentation

sealed class StartingGroupMenuScreenState(
	val isSuggestionsExpanded: Boolean
) {

	open fun copy(isSuggestionsExpanded: Boolean): StartingGroupMenuScreenState {
		return this
	}

	class NoGroupError(isSuggestionsExpanded: Boolean = false) : StartingGroupMenuScreenState(isSuggestionsExpanded) {

		override fun copy(isSuggestionsExpanded: Boolean) = NoGroupError(isSuggestionsExpanded)
	}

	class NoError(isSuggestionsExpanded: Boolean = false) : StartingGroupMenuScreenState(isSuggestionsExpanded) {

		override fun copy(isSuggestionsExpanded: Boolean) = NoError(isSuggestionsExpanded)
	}

	class Loading(isSuggestionsExpanded: Boolean = false) : StartingGroupMenuScreenState(isSuggestionsExpanded)
}