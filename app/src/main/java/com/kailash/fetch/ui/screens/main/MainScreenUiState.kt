package com.kailash.fetch.ui.screens.main

import com.kailash.fetch.data.model.Item

sealed class MainScreenUiState {
    data object Loading : MainScreenUiState()
    data class Success(val items: Map<Int, List<Item>>) : MainScreenUiState()
    data object Error : MainScreenUiState()
}