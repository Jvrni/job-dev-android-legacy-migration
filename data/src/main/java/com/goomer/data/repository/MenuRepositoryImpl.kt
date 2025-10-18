package com.goomer.data.repository

import com.goomer.data.MenuRepository
import com.goomer.data.local.MenuLocalDataSource
import com.goomer.data.models.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val localDataSource: MenuLocalDataSource) :
    MenuRepository {

    override suspend fun getList(): Flow<List<Menu>> = localDataSource.getMenuList().flowOn(
        Dispatchers.IO
    )
}