package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.KichuiParinaAppRoot
import com.example.ui.theme.KichuiParinaTheme
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val container = (application as KichuiParinaApp).container

        setContent {
            val mainViewModel: MainViewModel = viewModel(
                factory = MainViewModel.Factory(
                    repository = container.learningRepository,
                    prefsRepo = container.userPreferencesRepository
                )
            )
            val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()

            KichuiParinaTheme(themeMode = uiState.settings.themeMode) {
                KichuiParinaAppRoot(
                    container = container,
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}
