package com.mrjalal.randomgif.data.dataSource.remote.model

import com.mrjalal.randomgif.domain.repository.model.util.Dto
import com.mrjalal.randomgif.domain.repository.model.GifDomain
import com.squareup.moshi.Json

data class GifDto(
    @Json(name = "bitly_url") val bitlyUrl: String,
    val id: String,
    val rating: String,
    val title: String,
    val images: Images
) : Dto {
    override fun asUiModel(): GifDomain {
        return GifDomain(
            gifPageUrl = bitlyUrl,
            url = images.original.url,
            previewUrl = images.previewGif.url,
            id = id,
            rating = ratingLetterToAgeConvertor(rating),
            title = title
        )
    }
}

data class Images(
    val original: Image,
    @Json(name = "preview_gif") val previewGif: Image,
)

data class Image(
    val url: String? = null
)

fun ratingLetterToAgeConvertor(letter: String): String {
    return when (letter.uppercase()) {
        "U" -> "0"
        "G" -> "6"
        "PG" -> "7"
        "P" -> "12"
        "PG-13" -> "13"
        "T" -> "13"
        "M" -> "17"
        "R" -> "17"
        "NR" -> "17"
        "NC-17" -> "17"
        else -> letter
    }
}
