package com.goomer.ps.di

import com.goomer.ps.navigation.MenuNavigator
import com.goomer.ps.navigation.MenuNavigatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    @Provides
    fun provideMenuNavigator(): MenuNavigator = MenuNavigatorImpl()
}