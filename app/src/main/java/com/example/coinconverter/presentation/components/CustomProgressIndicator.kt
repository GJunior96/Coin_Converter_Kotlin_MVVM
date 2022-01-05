package com.example.coinconverter.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CustomProgressIndicator(isVisible: Boolean) {
    if (isVisible) {
        CircularProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.onBackground
        )
    }
}