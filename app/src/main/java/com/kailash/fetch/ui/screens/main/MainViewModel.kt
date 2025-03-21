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


/**
 * The [ViewModel] for the [MainScreen].
 *
 * This class is responsible for managing the UI state of the [MainScreen] and
 * fetching, filtering, and sorting the list of items from the [ItemRepository].
 *
 * @property repository The [ItemRepository] used to fetch items.
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ItemRepository) : ViewModel() {

    /**
     * The current UI state of the [MainScreen].
     *
     * This is a [MutableStateFlow] that emits [MainScreenUiState] objects.
     * The UI observes this state to update the screen.
     * It is initialized with [MainScreenUiState.Loading].
     */
    private val _mainScreenUiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)

    /**
     * A read-only [StateFlow] of the current [MainScreenUiState].
     *
     * This is exposed to the UI for observation.
     */
    val mainScreenUiState: StateFlow<MainScreenUiState> = _mainScreenUiState

    /**
     * Fetches, filters, and sorts the list of items from the [ItemRepository].
     *
     * This function performs the following operations:
     * 1. Sets the [mainScreenUiState] to [MainScreenUiState.Loading].
     * 2. Fetches the items from the [ItemRepository] using [ItemRepository.getItems].
     * 3. Filters out items with blank names.
     * 4. Sorts the items first by [Item.listId] and then by [Item.name].
     * 5. Groups the items by [Item.listId].
     * 6. Updates the [mainScreenUiState] to [MainScreenUiState.Success]with the filtered and sorted map of items.
     * 7. If an exception occurs during the process, updates the [mainScreenUiState] to [MainScreenUiState.Error].
     *
     * This function is launched in the [viewModelScope], so it will be canceled when the [ViewModel] is cleared.
     */
    fun fetchItems() {
        viewModelScope.launch {
            try {
                _mainScreenUiState.value = MainScreenUiState.Loading
                val items = repository.getItems()
                //Filter and sort
                val filteredMapItems = items
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy<Item> { it.listId }.thenBy { it.name })
                    .groupBy { it.listId }

                _mainScreenUiState.value = MainScreenUiState.Success(filteredMapItems)
            } catch (e: Exception) {
                _mainScreenUiState.value = MainScreenUiState.Error
            }
        }
    }
}
