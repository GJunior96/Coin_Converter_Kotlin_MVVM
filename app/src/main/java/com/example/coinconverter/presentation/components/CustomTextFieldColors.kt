package com.example.coinconverter.presentation.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color

@Composable
fun customTextFieldColors(isFocused: Boolean) : TextFieldColors {
    return outlinedTextFieldColors(
        textColor = if(isFocused) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary,
        disabledTextColor = Color.Gray,
        backgroundColor = MaterialTheme.colors.background,
        cursorColor = MaterialTheme.colors.onBackground,
        errorCursorColor = Color.Red,
        focusedBorderColor = MaterialTheme.colors.onBackground,
        unfocusedBorderColor = MaterialTheme.colors.primary,
        disabledBorderColor = Color.Gray,
        errorBorderColor = Color.Red,
        leadingIconColor = if(isFocused) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary,
        disabledLeadingIconColor = Color.Gray,
        errorLeadingIconColor = Color.Red,
        trailingIconColor = if(isFocused) MaterialTheme.colors.onBackground else MaterialTheme.colors.primary,
        disabledTrailingIconColor = Color.Gray,
        errorTrailingIconColor = Color.Red,
        focusedLabelColor = MaterialTheme.colors.onBackground,
        unfocusedLabelColor = MaterialTheme.colors.primary,
        disabledLabelColor = Color.Gray,
        errorLabelColor = Color.Red,
        placeholderColor = MaterialTheme.colors.primary,
        disabledPlaceholderColor = Color.Gray
    )
}