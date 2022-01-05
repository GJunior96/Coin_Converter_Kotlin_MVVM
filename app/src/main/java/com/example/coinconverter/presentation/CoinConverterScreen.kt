package com.example.coinconverter.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coinconverter.R
import com.example.coinconverter.presentation.components.CustomDropdownMenu
import com.example.coinconverter.presentation.components.CustomProgressIndicator
import com.example.coinconverter.presentation.components.CustomTopBar
import com.example.coinconverter.presentation.components.customTextFieldColors
import kotlinx.coroutines.launch

@Composable
fun CoinConverterScreen(
    viewModel: CoinConverterViewModel,
    isDarkTheme: MutableState<Boolean>
) {
    val scope = rememberCoroutineScope()
    val coins = stringArrayResource(id = R.array.coins)
    var amountText by remember { mutableStateOf("") }
    var fromCoinText by remember { mutableStateOf("") }
    var toCoinText by remember { mutableStateOf("") }
    var toCoinExpanded by remember { mutableStateOf(false) }
    var fromCoinExpanded by remember { mutableStateOf(false) }
    var toCoinFocus by remember { mutableStateOf(false) }
    var fromCoinFocus by remember { mutableStateOf(false) }
    var amountFocus by remember { mutableStateOf(false) }
    var clearFocus by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }
    var progressIndicator by remember { mutableStateOf(false) }
    val arrowIcon = Icons.Filled.ArrowDropDown
    var textAux = ""
    val sourceToCoin = remember { MutableInteractionSource() }
    val sourceFromCoin = remember { MutableInteractionSource() }
    val sourceAmount = remember { MutableInteractionSource() }

    if(clearFocus) {
        LocalFocusManager.current.clearFocus()
        clearFocus = false
    }

    if(sourceFromCoin.collectIsPressedAsState().value) {
        fromCoinExpanded = !fromCoinExpanded
    }

    if(sourceToCoin.collectIsPressedAsState().value) {
      toCoinExpanded = !toCoinExpanded
    }

    fromCoinFocus = sourceFromCoin.collectIsFocusedAsState().value
    toCoinFocus = sourceToCoin.collectIsFocusedAsState().value
    amountFocus = sourceAmount.collectIsFocusedAsState().value

    if(amountText.isNotBlank() && fromCoinText.isNotBlank() && toCoinText.isNotBlank()) {
        viewModel.convert(amountText, fromCoinText, toCoinText)
    }

    Scaffold(
        topBar = { CustomTopBar(title = "Coin Converter", isDarkTheme) },
        scaffoldState = rememberScaffoldState()
    ) {
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = fromCoinText,
                        onValueChange = { fromCoinText = it },
                        label = { Text(text = "From Coin") },
                        trailingIcon = {
                            Icon(imageVector = arrowIcon, contentDescription = "Dropdown Menu")
                        },
                        readOnly = true,
                        colors = customTextFieldColors(fromCoinFocus),
                        shape = MaterialTheme.shapes.medium,
                        interactionSource = sourceFromCoin,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.body1
                    )
                    CustomDropdownMenu(
                        isVisible = fromCoinExpanded,
                        coins = coins,
                        selectedItem = { fromCoinText = it },
                        onDismiss = {
                            fromCoinExpanded = false
                            if (fromCoinText.isBlank()) clearFocus = true
                        }
                    )
                }

                Box(modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = toCoinText,
                        onValueChange = { toCoinText = it },
                        label = { Text(text = "To Coin") },
                        trailingIcon = {
                            Icon(imageVector = arrowIcon, contentDescription = "Dropdown Menu")
                        },
                        readOnly = true,
                        colors = customTextFieldColors(toCoinFocus),
                        shape = MaterialTheme.shapes.medium,
                        interactionSource = sourceToCoin,
                        singleLine = true,
                        textStyle = MaterialTheme.typography.body1
                    )
                    CustomDropdownMenu(
                        isVisible = toCoinExpanded,
                        coins = coins,
                        selectedItem = { toCoinText = it },
                        onDismiss = {
                            toCoinExpanded = false
                            if(toCoinText.isBlank()) clearFocus = true
                        }
                    )
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        textAux = fromCoinText
                        fromCoinText = toCoinText
                        toCoinText = textAux
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colors.surface)
                    ) {
                        Icon(
                            modifier = Modifier.padding(8.dp),
                            painter = painterResource(id = R.drawable.ic_baseline_swap_horiz_48),
                            tint = MaterialTheme.colors.onSurface,
                            contentDescription = "Swap Coins"
                        )
                    }
                }
            }

            Box(modifier = Modifier.padding(8.dp)){
                OutlinedTextField(
                    value = amountText,
                    maxLines = 1,
                    onValueChange = { amountText = it },
                    label = { Text(text = "Amount") },
                    shape = MaterialTheme.shapes.medium,
                    colors = customTextFieldColors(isFocused = amountFocus),
                    interactionSource = sourceAmount,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    textStyle = MaterialTheme.typography.body1
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomProgressIndicator(isVisible = progressIndicator)
                Text(
                    text = result,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }
        scope.launch {
            when(viewModel.conversion.value) {
                is CoinConverterEvent.Success -> {
                    progressIndicator = false
                    result = (viewModel.conversion.value as CoinConverterEvent.Success).resultText
                }
                is CoinConverterEvent.Failure -> {
                    progressIndicator = false
                    result = (viewModel.conversion.value as CoinConverterEvent.Failure).errorText
                }
                is CoinConverterEvent.Loading -> {
                    progressIndicator = true
                }
                else -> Unit
            }
        }
    }
}