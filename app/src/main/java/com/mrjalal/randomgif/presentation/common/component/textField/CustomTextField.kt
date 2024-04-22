package com.mrjalal.randomgif.presentation.common.component.textField

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    textStyle: TextStyle,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    placeHolder: @Composable () -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit
) {
    val defaultColors = TextFieldDefaults.colors(
        focusedContainerColor = Transparent,
        unfocusedContainerColor = Transparent,
        disabledContainerColor = Transparent,
        focusedIndicatorColor = Transparent,
        unfocusedIndicatorColor = Transparent,
        disabledIndicatorColor = Transparent,
        cursorColor = Color.Black,
    )

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeHolder,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        textStyle = textStyle,
        enabled = enabled,
        readOnly = readOnly,
        shape = RoundedCornerShape(0.dp),
        colors = defaultColors,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = 1,
    )
}