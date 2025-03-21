package com.kailash.fetch.data.repository

import com.kailash.fetch.data.model.Item
import com.kailash.fetch.data.remote.api.FetchService
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val fetchService: FetchService) : ItemRepository {
    override suspend fun getItems(): List<Item> {
        return fetchService.getItems()
    }
}