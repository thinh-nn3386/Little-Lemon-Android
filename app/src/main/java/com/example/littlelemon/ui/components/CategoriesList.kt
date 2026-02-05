package com.example.littlelemon.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.LightGray
import com.example.littlelemon.ui.theme.LightGreen
import com.example.littlelemon.ui.theme.White
import java.util.Locale

@Composable
fun CategoriesList(
    selectedCategory: String,
    categories: List<String>,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            "Order for Delivery".toUpperCase(Locale.ROOT),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp).padding(start = 16.dp)
        )
        LazyRow {
            items(categories) { item ->
                CategoryItem(
                    item,
                    item == selectedCategory,
                    onClick=onSelect
                )
            }
        }
        Divider(
            modifier=Modifier.padding(top = 8.dp),
        )
    }
}

@Composable
fun CategoryItem(item: String, isSelected: Boolean, onClick: (String) -> Unit) {
    Box(modifier = Modifier
        .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 0.dp)
        .border(
            width = 1.dp,
            color = if (isSelected) Green else LightGreen,
            shape = RoundedCornerShape(8.dp)
        )
        .clip(RoundedCornerShape(8.dp))
        .background(if (isSelected) Green else LightGray)
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .clickable { onClick(item) }
    ) {
        Text(
            item,
            color = if (isSelected) White else Green,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
        )
    }
}
