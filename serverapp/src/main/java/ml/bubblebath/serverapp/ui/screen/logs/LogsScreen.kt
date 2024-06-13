package ml.bubblebath.serverapp.ui.screen.logs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ml.bubblebath.serverapp.ui.composable.LogColumnItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogsScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<LogsScreenViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(LogsScreenIntent.LoadLogs)
    }

    if (uiState.logs.isEmpty()) {
        Box(modifier = modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
            items(uiState.logs) { logEntry ->
                LogColumnItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, end = 4.dp),
                    logEntry = logEntry
                )
            }
        }
    }
}