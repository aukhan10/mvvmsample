package com.example.intercomtest.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.intercomtest.data.UniversityListDataSource
import com.example.intercomtest.data.UniversityListDataSourceImpl
import com.example.intercomtest.data.UniversityListRepository
import com.example.intercomtest.model.UniversityListResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val universityListRepository: UniversityListRepository
) : ViewModel() {

    // Expose screen UI state
    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    fun getList() {
        viewModelScope.launch {
            val list = universityListRepository.getUniversityList("United Arab Emirates")
            _uiState.update { currentState ->
                currentState.copy(
                    list = list
                )
            }
        }
    }
}

data class ListUiState(
    val list: List<UniversityListResponse> = mutableListOf()
)
