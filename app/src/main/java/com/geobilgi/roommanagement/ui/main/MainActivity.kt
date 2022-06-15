package com.geobilgi.roommanagement.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.geobilgi.roommanagement.ui.theme.RoomManagementTheme
import com.geobilgi.roommanagement.ui.theme.White
import com.geobilgi.roommanagement.ui.views.home.HomeScreen
import com.geobilgi.roommanagement.ui.views.storyDetail.StoryDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MainActivityViewModel by viewModels()
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
                                Text(text = "Oda Yönetimi",
                                    color = Color.Black,
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                        }
                    }
                ) {
                    NavigationHost()
                }

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
            "story_detail_screen/{storyId}",
            arguments = listOf(
                navArgument("storyId") {
                    type = NavType.StringType
                }
            )
        ) {
            val storyId = remember {
                it.arguments?.getString("storyId")
            }
            StoryDetailScreen(
                storyId = storyId ?: "",
                navController = navController
            )
        }
    }
}

