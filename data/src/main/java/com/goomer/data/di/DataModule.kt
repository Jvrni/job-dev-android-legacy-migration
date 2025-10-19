package com.goomer.data.di

import android.content.Context
import com.google.gson.Gson
import com.goomer.data.MenuRepository
import com.goomer.data.local.JsonReader
import com.goomer.data.local.MenuLocalDataSource
import com.goomer.data.repository.MenuRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideJsonReader(@ApplicationContext context: Context) = JsonReader(context)

    @Provides
    fun provideLocalDataSource(
        jsonReader: JsonReader,
        gson: Gson
    ) = MenuLocalDataSource(jsonReader, gson)

    @Singleton
    @Provides
    fun provideService(localDataSource: MenuLocalDataSource): MenuRepository = MenuRepositoryImpl(localDataSource)
}