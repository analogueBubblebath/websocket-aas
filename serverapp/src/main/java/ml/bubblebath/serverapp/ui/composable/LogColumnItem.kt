package ml.bubblebath.serverapp.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ml.bubblebath.Log

@Composable
fun LogColumnItem(modifier: Modifier = Modifier, logEntry: Log) {
    OutlinedCard(
        modifier = modifier,
        border = BorderStroke(width = 1.dp, color = logEntry.logLevel.asColor())
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = logEntry.id.toString())
            Text(text = logEntry.message)
        }
    }
}
