package com.kailash.fetch.ui.screens.main

import com.kailash.fetch.data.model.Item

sealed class MainScreenUiState {
    data object isLoading : MainScreenUiState()
    data class onSuccess(val items: Map<Int, List<Item>>) : MainScreenUiState()
    data class onError(val message: String) : MainScreenUiState()
}