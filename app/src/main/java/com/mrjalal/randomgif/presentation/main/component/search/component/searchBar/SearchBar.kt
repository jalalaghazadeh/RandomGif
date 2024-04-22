package com.mrjalal.randomgif.presentation.main.component.search.component.searchBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrjalal.randomgif.R
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray4
import com.mrjalal.randomgif.presentation.app.ui.theme.Gray_9
import com.mrjalal.randomgif.presentation.common.helper.noRippleClickable
import com.mrjalal.randomgif.presentation.common.component.textField.LeadingIcon
import com.mrjalal.randomgif.presentation.common.component.textField.CustomTextField
import com.mrjalal.randomgif.presentation.common.component.textField.PlaceHolder
import com.mrjalal.randomgif.presentation.common.component.textField.TrailingIcon


@Composable
fun SearchBar(
    active: Boolean,
    query: String,
    onSearchBarStatusChange: (Boolean) -> Unit,
    onQueryChanged: (String) -> Unit,
    resetSearchQuery: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester =  FocusRequester()

    LaunchedEffect(active) {
        if (active) {
            focusRequester.requestFocus()
        } else {
            focusManager.clearFocus()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 12.dp)
            .noRippleClickable {
                if (!active) {
                    onSearchBarStatusChange(true)
                    focusRequester.requestFocus()
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = active) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .noRippleClickable { onSearchBarStatusChange(false) },
                colorFilter = ColorFilter.tint(Gray4)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .background(Gray_9, shape = RoundedCornerShape(8.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextField(
                value = query,
                onValueChange = onQueryChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                enabled = active,
                readOnly = !active,
                textStyle = MaterialTheme.typography.bodyLarge,
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                keyboardOptions = KeyboardOptions(
                    autoCorrect = false,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
                placeHolder = {
                    PlaceHolder(text = stringResource(id = R.string.search))
                },
                leadingIcon = { LeadingIcon() },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        TrailingIcon(
                            onClick = {
                                onSearchBarStatusChange(false)
                                resetSearchQuery()
                            }
                        )
                    }
                }
            )
        }
    }
}

@Preview(widthDp = 360)
@Composable
fun SearchBarPrev() {
    SearchBar(
        active = true,
        query = "",
        onQueryChanged = {},
        onSearchBarStatusChange = {},
        resetSearchQuery = {}
    )
}