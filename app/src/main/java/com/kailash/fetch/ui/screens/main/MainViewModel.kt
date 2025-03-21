package com.kailash.fetch.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kailash.fetch.data.model.Item
import com.kailash.fetch.data.repository.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {

    private val _mainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val mainScreenUiState: StateFlow<MainScreenUiState> = _mainScreenUiState

    fun fetchItems() {
        viewModelScope.launch {
            try {
                val items = repository.getItems()
                //Filter and sort
                val filteredMapItems = items
                    .filter { !it.name.isNullOrBlank() && it.name.isNotEmpty() }
                    .sortedWith(compareBy<Item> { it.listId }.thenBy { it.name })
                    .groupBy { it.listId }

                _mainScreenUiState.value = MainScreenUiState.Success(filteredMapItems)
            } catch (e: Exception) {
                _mainScreenUiState.value = MainScreenUiState.Error
            }
        }
    }
}
