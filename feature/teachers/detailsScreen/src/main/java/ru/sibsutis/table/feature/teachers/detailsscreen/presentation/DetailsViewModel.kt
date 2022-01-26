package ru.sibsutis.table.feature.teachers.detailsscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sibsutis.table.feature.teachers.detailsscreen.domain.usecase.GetTeacherUseCase

class DetailsViewModel(
	private val getTeacherUseCase: GetTeacherUseCase,
	private val name: String
) : ViewModel() {

	private val _state = MutableStateFlow<DetailsState>(DetailsState.Initialize)
	val state = _state.asStateFlow()

	fun loadInfo() {

		_state.value = DetailsState.Loading
		viewModelScope.launch {
			val content = getTeacherUseCase(name)
			_state.value = DetailsState.Content(content)
		}
	}
}