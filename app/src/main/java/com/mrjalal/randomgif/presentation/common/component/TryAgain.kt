package com.mrjalal.randomgif.presentation.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray4
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray_4
import com.mrjalal.randomgif.presentation.common.model.NetworkErrorMessageType

@Composable
fun TryAgain(
    errorType: String,
    onTryAgain: () -> Unit,
    modifier: Modifier = Modifier
) {
    val message = when (errorType) {
        NetworkErrorMessageType.SERVER_ERROR.name -> stringResource(id = R.string.server_error)
        NetworkErrorMessageType.GENERAL.name -> stringResource(id = R.string.general_network_error)
        else -> ""
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (message.isNotEmpty()) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                ),
                color = Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Button(
            onClick = onTryAgain,
            colors = ButtonDefaults.buttonColors(
                containerColor = Gray4
            )
        ) {
            Text(
                text = stringResource(id = R.string.try_again),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                ),
                color = Gray_4,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TryAgainPrev() {
    TryAgain(
        errorType = NetworkErrorMessageType.GENERAL.name,
        onTryAgain = {}
    )
}