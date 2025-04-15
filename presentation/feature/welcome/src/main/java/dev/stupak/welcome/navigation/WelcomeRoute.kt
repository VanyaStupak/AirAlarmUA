package dev.stupak.welcome.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.stupak.welcome.WelcomeScreen
import dev.stupak.welcome.WelcomeViewModel

@SuppressLint("NewApi")
@Composable
internal fun WelcomeRoute(
    onNavigateToMain: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: WelcomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.welcomeState.collectAsStateWithLifecycle()

    WelcomeScreen(
        onBackClick = onBackClick,
        onNavigateToMain,
        onAction = viewModel::onAction,
        uiState = uiState,
    )
}
