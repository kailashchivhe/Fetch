package com.kailash.fetch.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kailash.fetch.R
import com.kailash.fetch.ui.components.main.ErrorComponent
import com.kailash.fetch.ui.components.main.ItemListComponent
import com.kailash.fetch.ui.components.main.LoadingComponent

@Composable
fun MainScreen(modifier: Modifier, viewModel: MainViewModel) {
    val uiState = viewModel.mainScreenUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchItems()
    }
    when(uiState.value) {
        is MainScreenUiState.Loading -> {
            //Show Progress UI
            LoadingComponent(modifier)
        }
        is MainScreenUiState.Success -> {
            //Show Items UI
            ItemListComponent(modifier, (uiState.value as MainScreenUiState.Success).items)
        }
        is MainScreenUiState.Error -> {
            //Show Error UI
            ErrorComponent(modifier, LocalContext.current.getString(R.string.error_message)) {
                viewModel.fetchItems()
            }
        }
    }
}