package com.example.littlelemon.data.repository

import com.example.littlelemon.data.database.MenuDao
import com.example.littlelemon.data.database.toDomain
import com.example.littlelemon.data.model.MenuItem
import com.example.littlelemon.data.api.MenuApi
import com.example.littlelemon.data.api.toEntity
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.map

@Singleton
class MenuRepository @Inject constructor(
    private val menuDao: MenuDao,
) {

    fun observeMenu(): Flow<List<MenuItem>> {
        return menuDao.getAllMenuItems().map { entities ->
            entities.map { entity ->
                entity.toDomain()
            }
        }
    }

    suspend fun refreshMenuIfNeeded() {
        if (menuDao.isEmpty()) {
            val remoteMenu = MenuApi.fetchMenu()
            menuDao.insertAll(remoteMenu.map { it.toEntity() })
        }
    }
}