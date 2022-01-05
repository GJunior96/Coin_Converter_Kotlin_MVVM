package com.example.coinconverter.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coinconverter.R

@Composable
fun CustomTopBar(title: String, darkTheme: MutableState<Boolean>) {
    var icon = if (darkTheme.value) R.drawable.ic_baseline_wb_sunny_24
            else R.drawable.ic_baseline_bedtime_24

    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(Modifier.wrapContentHeight()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .weight(1f)
                        .padding(start = 40.dp)
                ) {
                    ProvideTextStyle(value = MaterialTheme.typography.h1) {
                        CompositionLocalProvider(
                            LocalContentAlpha provides ContentAlpha.high,
                        ) {
                            Text(
                                modifier = Modifier,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                color = MaterialTheme.colors.onBackground,
                                text = title,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Column(modifier = Modifier.wrapContentSize()) {
                    CompositionLocalProvider(
                        LocalContentAlpha provides ContentAlpha.high,
                    ) {
                        IconButton(
                            onClick = {
                                darkTheme.value = !darkTheme.value
                                icon = if (darkTheme.value) R.drawable.ic_baseline_wb_sunny_24
                                else R.drawable.ic_baseline_bedtime_24
                            },
                            enabled = true
                        ) {
                            Icon(
                                painter = painterResource(icon),
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = "Turn on/off Dark Theme "
                            )
                        }
                    }
                }
            }
        }
    }
}