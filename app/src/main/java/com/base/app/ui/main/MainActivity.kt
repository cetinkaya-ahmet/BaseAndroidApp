package com.base.app.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.base.app.ui.theme.RoomManagementTheme
import com.base.app.ui.theme.White
import com.base.app.ui.views.home.HomeScreen
import com.base.app.ui.views.detail.StoryDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomManagementTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(backgroundColor = White,
                            elevation = 4.dp
                        ) {
                            Row(horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "App",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    },
                    content = {
                        NavigationHost()
                    }
                )

            }
        }
    }
}

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home_screen"
    ) {
        composable("home_screen") {
            HomeScreen(navController = navController)
        }
        composable(
            "detail_screen/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            val id = remember {
                it.arguments?.getString("id")
            }
            StoryDetailScreen(
                id = id ?: "",
                navController = navController
            )
        }
    }
}

