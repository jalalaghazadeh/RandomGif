package com.mrjalal.randomgif.presentation.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray

@Composable
fun ErrorMessage(text: String, modifier: Modifier = Modifier) {
    Text(
        text,
        style = MaterialTheme.typography.bodyMedium.copy(
            fontWeight = FontWeight.Medium,
        ),
        color = Gray,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .then(modifier)
    )
}

@Preview(showBackground = true)
@Composable
private fun ErrorMessagePrev() {
    ErrorMessage(
        text = stringResource(id = R.string.fetch_gif_error)
    )
}