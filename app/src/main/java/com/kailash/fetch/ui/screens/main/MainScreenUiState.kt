package com.kailash.fetch.ui.screens.main

import com.kailash.fetch.data.model.Item

/**
 * Represents the different states of the main screen.
 *
 * This sealed class defines the possible states that the [MainScreen]can be in,
 * including loading, success (with a map of items), and error.
 */
sealed class MainScreenUiState {
    /**
     * Represents the loading state of the main screen.
     * This state indicates that the main screen is currently fetching data.
     * */
    data object Loading : MainScreenUiState()

    /**
     * Represents the successful state of the main screen.
     * This state indicates that the main screen has successfully fetched data
     * and contains a map of items to display.
     *
     * @property items A map where the key is an [Int] and the value is a [List] of [Item] objects.
     */
    data class Success(val items: Map<Int, List<Item>>) : MainScreenUiState()

    /**
     * Represents the error state of the main screen.
     * This state indicates that an error occurred while fetching data.
     */
    data object Error : MainScreenUiState()
}