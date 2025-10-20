package com.goomer.domain

import com.goomer.domain.models.Menu
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMenuList @Inject constructor(private val repository: MenuRepository) {
    suspend operator fun invoke(): Flow<List<Menu>> {
        return repository.getList()
    }
}