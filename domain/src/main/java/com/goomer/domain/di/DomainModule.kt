package com.goomer.domain.di

import com.goomer.domain.GetMenuList
import com.goomer.domain.MenuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Singleton
    @Provides
    fun provideDomain(repository: MenuRepository) = GetMenuList(repository)
}