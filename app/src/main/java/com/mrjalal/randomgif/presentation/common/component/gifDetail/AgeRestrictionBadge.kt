package com.mrjalal.randomgif.presentation.common.component.gifDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AgeRestrictionBadge(rating: String) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(30.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${rating.uppercase()}+",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun AgeRestrictionBadgePrev() {
    AgeRestrictionBadge(rating = "18")
}