package com.hedimisawi.smartconseiltest.ui.main_activity.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.navigation.PostsNavigation
import com.hedimisawi.smartconseiltest.ui.theme.backgroundBlue

@Composable
fun PostsScreen(modifier: Modifier = Modifier, viewModel: PostsViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    val navController = rememberNavController()
    Scaffold(modifier = modifier.fillMaxSize(), snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundBlue)
                .padding(innerPadding)
        ) {
            PostsNavigation(navController = navController, viewModel = viewModel, snackbarHostState = snackbarHostState)
        }
    }
}