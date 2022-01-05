package com.example.coinconverter.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat
import com.example.coinconverter.R
import com.example.coinconverter.databinding.ActivityMainBinding
import com.example.coinconverter.ui.theme.CoinConverterAppTheme
import com.example.coinconverter.util.JsonFile
import com.example.coinconverter.util.getJsonData
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val json = getJsonData(this, "api.json")
        JsonFile.jsonFile = json ?: ""
        val viewModel: CoinConverterViewModel by viewModels()


        setContent {
            val systemTheme = isSystemInDarkTheme()
            val isDarkTheme = remember { mutableStateOf(systemTheme) }

            val themeFunction: @Composable (content: @Composable () -> Unit) -> Unit =
                when(isDarkTheme.value) {
                    true -> {
                        content -> CoinConverterAppTheme(true, content)
                        this.window.statusBarColor = ContextCompat.getColor(this, R.color.pink)
                    }
                    false -> {
                        content -> CoinConverterAppTheme(false, content)
                        this.window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
                    }
                }

            themeFunction.invoke {
                CoinConverterAppTheme(darkTheme = isDarkTheme.value) {
                    CoinConverterScreen(viewModel = viewModel, isDarkTheme = isDarkTheme)
                }
            }
        }
    }
}