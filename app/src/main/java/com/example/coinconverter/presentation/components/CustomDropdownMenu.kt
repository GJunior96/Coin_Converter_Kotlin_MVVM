package com.example.coinconverter.presentation.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex

@Composable
fun CustomDropdownMenu(
    isVisible: Boolean,
    coins: Array<String>,
    selectedItem: (String) -> Unit,
    onDismiss: () -> Unit
){
    if (isVisible) {
        DropdownMenu(
            expanded = isVisible,
            onDismissRequest = { onDismiss() }
        ) {
            coins.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedItem(label)
                    onDismiss()
                }) {
                    Text(text = label)
                }
            }
        }
    }
}