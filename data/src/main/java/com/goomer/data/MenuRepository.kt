package com.goomer.data

import com.goomer.data.models.Menu
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getList(): Flow<List<Menu>>
}