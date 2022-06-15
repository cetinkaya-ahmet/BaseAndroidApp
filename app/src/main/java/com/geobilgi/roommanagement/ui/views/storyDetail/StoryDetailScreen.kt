package com.geobilgi.roommanagement.ui.views.storyDetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.geobilgi.roommanagement.ui.theme.Teal200
import com.geobilgi.roommanagement.ui.theme.White

@Composable
fun StoryDetailScreen(
    viewModel: StoryDetailViewModel = hiltViewModel(),
    storyId: String,
    navController: NavController
) {
    Surface(
        color = Teal200,
        modifier = Modifier.fillMaxSize()
    ) {

    }
}