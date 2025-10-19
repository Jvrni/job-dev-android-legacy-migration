package com.goomer.data.local

import android.content.Context

class JsonReader(private val context: Context) {

    fun readAsset(fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }
}