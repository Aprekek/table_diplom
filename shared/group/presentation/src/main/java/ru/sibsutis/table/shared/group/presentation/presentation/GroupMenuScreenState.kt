package ru.sibsutis.table.shared.group.presentation.presentation

sealed class GroupMenuScreenState(
	val isSuggestionsExpanded: Boolean
) {

	open fun copy(isSuggestionsExpanded: Boolean): GroupMenuScreenState {
		return this
	}

	class NoGroupError(isSuggestionsExpanded: Boolean = false) : GroupMenuScreenState(isSuggestionsExpanded) {

		override fun copy(isSuggestionsExpanded: Boolean) = NoGroupError(isSuggestionsExpanded)
	}

	class NoError(isSuggestionsExpanded: Boolean = false) : GroupMenuScreenState(isSuggestionsExpanded) {

		override fun copy(isSuggestionsExpanded: Boolean) = NoError(isSuggestionsExpanded)
	}

	class Loading(isSuggestionsExpanded: Boolean = false) : GroupMenuScreenState(isSuggestionsExpanded)
}