package com.mrjalal.randomgif.presentation.common.component.gifDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R

@Composable
fun Error(modifier: Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ic_fail),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center),
        )
    }
}