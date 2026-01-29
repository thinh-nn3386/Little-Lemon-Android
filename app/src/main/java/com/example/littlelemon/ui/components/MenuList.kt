package com.example.littlelemon.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.littlelemon.data.database.MenuItemRoom

@Composable
fun MenuList(
    data: List<MenuItemRoom>
) {
    LazyColumn {
        items(
            items = data,
            itemContent = { menuItem ->
                MenuItem(item = menuItem)
            }
        )
    }
}


@Composable
fun MenuItem(
    item: MenuItemRoom
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        // Aligns the image to the bottom of the Row's height
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            // Use weight(1f) to fill all available space minus the fixed width of the image
            modifier = Modifier.weight(1f).padding(end = 8.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = item.description,
                maxLines = 2,
                modifier = Modifier.padding(vertical = 8.dp),
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "$${item.price}",
                style = MaterialTheme.typography.titleMedium
            )
        }
        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
