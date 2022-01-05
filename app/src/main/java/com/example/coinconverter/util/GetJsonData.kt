package com.example.coinconverter.util

import android.content.Context
import android.util.Log
import java.io.IOException

fun getJsonData(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return  jsonString
}