package com.example.littlelemon.services

import com.example.littlelemon.data.database.MenuItemEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.Int

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
)

fun MenuItemNetwork.toEntity(): MenuItemEntity {
    return MenuItemEntity(
        id = id,
        title = title,
        price = price,
        description = description,
        image = image,
        category = category
    )
}

