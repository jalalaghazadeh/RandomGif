package com.mrjalal.randomgif.presentation.common.component.gifDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.GlideSubcomposition
import com.bumptech.glide.integration.compose.RequestState
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray_4
import com.mrjalal.randomgif.presentation.common.component.Loading

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GifAnimator(
    url: String?,
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier
) {
    Box(
        modifier = containerModifier
            .background(
                color = Gray_4,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        GlideSubcomposition(url) {
            when (state) {
                is RequestState.Failure -> {
                    Error(modifier = containerModifier)
                }

                is RequestState.Loading -> {
                    Loading()
                }

                is RequestState.Success -> {
                    GlideImage(
                        model = url,
                        contentDescription = null,
                        modifier = modifier.then(Modifier.fillMaxSize()),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Preview(widthDp = 100, heightDp = 100)
@Composable
fun GifAnimatorPrev() {
    GifAnimator(
        url = ""
    )
}


