package com.kailash.fetch.data.repository

import com.kailash.fetch.data.model.Item
import com.kailash.fetch.data.remote.api.FetchService
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val fetchService: FetchService) : ItemRepository {

    /**
     * Retrieves a list of [Item] objects from the remote data source.
     * This function fetches the items from the network using the [fetchService].
     *
     * @return A [List] of [Item] objects.
     * @throws Exception If there is an error during the network request.
     */
    override suspend fun getItems(): List<Item>? {
        return fetchService.getItems()
    }
}