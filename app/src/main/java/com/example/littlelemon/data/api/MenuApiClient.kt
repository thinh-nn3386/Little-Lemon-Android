package com.example.littlelemon.data.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

// Add this Network Service Object
object MenuApi {
    private const val MENU_URL =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    suspend fun fetchMenu(): List<MenuItemNetwork> {
        return try {
            val response: MenuNetwork = httpClient.get(MENU_URL).body()
            response.menu
        } catch (e: Exception) {
            emptyList() // Return empty list on network error
        }
    }
}