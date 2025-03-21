package com.kailash.fetch.data.repository

import com.kailash.fetch.data.model.Item

interface ItemRepository {
    suspend fun getItems(): List<Item>
}