package com.goomer.data.local

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.goomer.data.models.Menu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuLocalDataSource @Inject constructor(
    private val jsonReader: JsonReader,
    private val gson: Gson
) {
    fun getMenuList(): Flow<List<Menu>> = flow {
        val json = jsonReader.readAsset("menu.json")
        val type = object : TypeToken<List<Menu>>() {}.type

        emit(gson.fromJson(json, type))
    }
}