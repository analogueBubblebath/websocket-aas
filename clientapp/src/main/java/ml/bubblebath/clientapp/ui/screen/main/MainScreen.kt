package ml.bubblebath.clientapp.ui.screen.main

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ml.bubblebath.clientapp.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier, onConfigClicked: () -> Unit) {
    val viewModel = koinViewModel<MainScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        if (uiState.isClientRunning) {
            Button(onClick = { viewModel.handleIntent(MainScreenIntent.ClientPause) }) {
                Text(stringResource(id = R.string.client_pause))
            }
        } else {
            Button(onClick = {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://dzen.ru")))
                viewModel.handleIntent(MainScreenIntent.ClientStart)
            }) {
                Text(stringResource(id = R.string.client_start))
            }
        }
        Button(onClick = onConfigClicked, enabled = !uiState.isClientRunning) {
            Text(text = stringResource(id = R.string.client_config))
        }
        Button(onClick = {
            context.startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }) {
            Text(text = stringResource(R.string.client_open_settings))
        }
    }
}
