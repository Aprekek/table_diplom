package ru.sibsutis.table.features.teachers.listscreen.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sibsutis.table.features.teachers.listscreen.domain.entity.Teacher
import ru.sibsutis.table.features.teachers.listscreen.domain.usecase.GetTeachersListUseCase
import ru.sibsutis.table.features.teachers.listscreen.domain.usecase.UpdateLocalTeachersStorageUseCase

class TeachersListViewModel(
	private val updateLocalTeachersStorageUseCase: UpdateLocalTeachersStorageUseCase,
	private val getTeachersListUseCase: GetTeachersListUseCase
) : ViewModel() {

	private val _state = MutableStateFlow<TeachersListState>(TeachersListState.Initialize)
	val state = _state.asStateFlow()

	var localStorageTeacherChangesJob: Job? = null

	fun initialize() {
		updateDatabaseWithRemoteData()
		getTeachers()
	}

	private fun updateDatabaseWithRemoteData() {
		viewModelScope.launch {
			try {
				_state.value = TeachersListState.Loading
				updateLocalTeachersStorageUseCase()
			} catch (e: Exception) {
				_state.value = TeachersListState.Error
				Log.d("TeachersList", "${this.javaClass.name} ${e.message}")
			}
		}
	}

	private fun getTeachers() {
		localStorageTeacherChangesJob?.cancel()
		localStorageTeacherChangesJob = getTeachersListUseCase()
			.onEach { newList ->
				val groupedList = newList.groupBy { it.name[0] }.toSortedMap()
				_state.value = TeachersListState.Content(groupedList)
			}
			.launchIn(viewModelScope)
	}

	fun navigateToSelectedTeacher(item: Teacher) {
		Log.i("click", "${item.name} selected")
	}
}