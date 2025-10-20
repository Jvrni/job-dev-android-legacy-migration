package com.goomer.domain

import com.goomer.domain.models.Menu
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    suspend fun getList(): Flow<List<Menu>>
}