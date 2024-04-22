package com.mrjalal.randomgif.presentation.common.component.textField

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R

@Composable
fun TrailingIcon(onClick: () -> Unit) {
    Image(
        painter = painterResource(id = R.drawable.ic_close),
        contentDescription = null,
        modifier = Modifier
            .padding(12.dp)
            .size(24.dp)
            .clickable { onClick() },
        colorFilter = ColorFilter.tint(Color.Gray)
    )
}