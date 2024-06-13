package ml.bubblebath.serverapp.ui.screen.config

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ml.bubblebath.testtask.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConfigScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<ConfigScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = uiState.port,
            onValueChange = { viewModel.handleIntent(ConfigScreenIntent.PortInput(newText = it)) })

        Button(
            onClick = { viewModel.handleIntent(ConfigScreenIntent.PortSave) },
            enabled = uiState.isSaveButtonEnabled
        ) {
            Text(text = stringResource(id = R.string.config_save))
        }
    }
}
