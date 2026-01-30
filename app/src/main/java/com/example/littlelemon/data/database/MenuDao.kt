package com.example.littlelemon.data.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.littlelemon.data.model.MenuItem
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "menu_item")
data class MenuItemEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

fun MenuItemEntity.toDomain(): MenuItem {
    return MenuItem(
        id = id,
        title = title,
        price = price,
        description = description,
        image = image,
        category = category
    )
}

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu_item")
    fun getAllMenuItems(): Flow<List<MenuItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(menuItems: List<MenuItemEntity>)

    @Query("SELECT (SELECT COUNT(*) FROM menu_item) == 0")
    suspend fun isEmpty(): Boolean
}