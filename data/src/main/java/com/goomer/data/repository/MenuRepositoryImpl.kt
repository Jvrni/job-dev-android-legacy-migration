package com.goomer.data.repository

import com.goomer.data.local.MenuLocalDataSource
import com.goomer.domain.MenuRepository
import com.goomer.domain.models.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val localDataSource: MenuLocalDataSource) :
    MenuRepository {

    override suspend fun getList(): Flow<List<Menu>> = localDataSource.getMenuList().map {
        it.map { item -> item.toDomain() }
    }.flowOn(Dispatchers.IO)
}