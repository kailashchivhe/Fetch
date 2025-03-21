package com.kailash.fetch.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id")
    val id: Int,
    @SerializedName("listId")
    val listId: Int,
    @SerializedName("name")
    val name: String?
)
