package com.mrjalal.randomgif.presentation.common.component.gifDetail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray4
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray_4
import com.mrjalal.randomgif.presentation.common.helper.getScreenWidth
import com.mrjalal.randomgif.presentation.common.model.GifUiModel

@Composable
fun GifDetailContent(
    modifier: Modifier = Modifier,
    item: GifUiModel,
    title: String? = null,
) {
    val gifContainerSize = getScreenWidth() - 20.dp * 2

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        title?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Gray4,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        GifAnimator(
            url = item.url,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            containerModifier = Modifier
                .size(gifContainerSize)
                .padding(bottom = 20.dp)
                .background(
                    color = Gray_4,
                    shape = RoundedCornerShape(8.dp)
                )
        )

        GifDetailInfoRow(item)
    }
}

@Composable
fun GifDetailInfoRow(item: GifUiModel) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.title.ifEmpty { stringResource(id = R.string.no_title) },
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.Black
            )
            Text(
                text = item.gifPageUrl,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    openLinkInBrowser(
                        context = context,
                        url = item.gifPageUrl
                    )
                }
            )
        }
        AgeRestrictionBadge(item.rating)
    }
}

fun openLinkInBrowser(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}


@Preview(backgroundColor = 0xffffffff, showBackground = true)
@Composable
private fun GifDetailPrev(
    @PreviewParameter(GifDetailPreviewParameterProvider::class) parameter: GifDetailParameter
) {
    GifDetailContent(item = parameter.item)
}

class GifDetailPreviewParameterProvider : PreviewParameterProvider<GifDetailParameter> {
    override val values: Sequence<GifDetailParameter>
        get() = sequenceOf(
            GifDetailParameter(
                item = GifUiModel(
                    gifPageUrl = "http://gph.is/1LjlEFE",
                    url = "https://giphy.com/embed/JIX9t2j0ZTN9S",
                    previewUrl = "https://giphy.com/embed/JIX9t2j0ZTN9S",
                    id = "JIX9t2j0ZTN9S",
                    rating = "g",
                    title = "Cat Working GIF",
                )
            )
        )
}

data class GifDetailParameter(
    val item: GifUiModel
)