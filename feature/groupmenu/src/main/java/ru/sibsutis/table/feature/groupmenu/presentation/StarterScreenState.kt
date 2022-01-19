package ru.sibsutis.table.feature.groupmenu.presentation

sealed class StarterScreenState(
	val isSuggestionsExpanded: Boolean
) {

	open fun copy(isSuggestionsExpanded: Boolean): StarterScreenState {
		return this
	}

	class NoGroupError(isSuggestionsExpanded: Boolean = false) : StarterScreenState(isSuggestionsExpanded) {

		override fun copy(isSuggestionsExpanded: Boolean) = NoGroupError(isSuggestionsExpanded)
	}

	class NoError(isSuggestionsExpanded: Boolean = false) : StarterScreenState(isSuggestionsExpanded) {

		override fun copy(isSuggestionsExpanded: Boolean) = NoError(isSuggestionsExpanded)
	}

	class Loading(isSuggestionsExpanded: Boolean = false) : StarterScreenState(isSuggestionsExpanded)
}