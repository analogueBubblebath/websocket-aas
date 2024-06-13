package ml.bubblebath.serverapp.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ml.bubblebath.testtask.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onConfigClicked: () -> Unit,
    onLogsClicked: () -> Unit
) {
    val viewModel = koinViewModel<MainScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.handleIntent(MainScreenIntent.StartServer) }) {
            if (uiState.isServerStarted) {
                Text(text = stringResource(id = R.string.stop_server))
            } else {
                Text(text = stringResource(id = R.string.start_server))
            }
        }
        Button(onClick = onConfigClicked) {
            Text(text = stringResource(id = R.string.config_server))
        }
        Button(onClick = onLogsClicked) {
            Text(text = stringResource(id = R.string.logs_server))
        }
    }
}
