package dev.stupak.main.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.stupak.main.MainScreen
import dev.stupak.main.MainViewModel

@SuppressLint("NewApi")
@Composable
internal fun MainRoute(
    initialPage: Int = 0,
    onSettingsButtonClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val uiState by viewModel.mainUiState.collectAsStateWithLifecycle()
    MainScreen(
        uiState = uiState,
        onSettingsButtonClick = onSettingsButtonClick,
        regionName = uiState.region,
        isFirstRun = uiState.isFirstRun,
        initialPage = initialPage,
    )
}
