package com.example.littlelemon.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.littlelemon.data.database.AppDatabase
import com.example.littlelemon.data.database.MenuItemRoom
import com.example.littlelemon.R
import com.example.littlelemon.services.MenuApi
import com.example.littlelemon.ui.components.MenuList
import com.example.littlelemon.navigation.ProfileRoute
import com.example.littlelemon.ui.theme.Green


@Composable
fun HomeScreen(
    navController: NavController
) {
    val focusManager = LocalFocusManager.current

    // Define a state for the search text at the top of HomeScreen
    var searchPhrase by remember { mutableStateOf("") }

    val context = LocalContext.current

    // 1. Initialize Database (Ideally this should be in a ViewModel or Singleton)
    val database = remember {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    // 2. Observe the database as state
    // Whenever the database changes, databaseMenuItems will update automatically
    val databaseMenuItems by database.menuDao().getAllMenuItems()
        .collectAsState(initial = emptyList())

    // 3. Fetch from Network and Save to Database
    LaunchedEffect(Unit) {
        // Check if database is empty to avoid unnecessary network calls
        if (database.menuDao().isEmpty()) {
            val networkItems = MenuApi.fetchMenu()

            println("Network Items: $networkItems")

            // Map Network objects to Room objects
            val roomItems = networkItems.map {
                MenuItemRoom(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    image = it.image,
                    category = it.category
                )
            }

            // Insert into database (The UI will update automatically because of the Flow)
            database.menuDao().insertAll(roomItems)
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(200.dp)
                        .height(56.dp),
                    contentScale = ContentScale.Fit
                )
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp)
                        .width(56.dp)
                        .height(56.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .clickable {
                            navController.navigate(ProfileRoute)
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }) {
            TopPanel(
                searchPhrase,
                onChangeSearch = { searchPhrase = it }
            )

            MenuList(
                data = databaseMenuItems
            )
        }
    }
}

@Composable
fun TopPanel(
    search: String,
    onChangeSearch: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Green)
            .padding(16.dp)
    ) {
        Text(
            text = "Little Lemon",
            color = Color.Yellow,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Chicago",
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 16.dp)
                )
            }

            Image(
                painter = painterResource(R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .width(156.dp)
                    .height(156.dp)
                    .clip(RoundedCornerShape(28.dp)),
                contentScale = ContentScale.Crop
            )
        }

        // Add this at the <caret> position
        OutlinedTextField(
            value = search,
            onValueChange = onChangeSearch,
            placeholder = { Text("Enter search phrase") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            singleLine = true
        )
    }
}

