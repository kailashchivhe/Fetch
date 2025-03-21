package com.kailash.fetch.data.remote.api

import com.kailash.fetch.data.model.Constants
import com.kailash.fetch.data.model.Item
import retrofit2.http.GET

interface FetchService {
    @GET(Constants.ITEMS_ENDPOINT)
    suspend fun getItems(): List<Item>
}