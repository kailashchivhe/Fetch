package com.kailash.fetch.di

import com.kailash.fetch.data.remote.api.FetchService
import com.kailash.fetch.data.repository.ItemRepository
import com.kailash.fetch.data.repository.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideItemRepository(fetchService: FetchService) : ItemRepository {
        return ItemRepositoryImpl(fetchService)
    }
}