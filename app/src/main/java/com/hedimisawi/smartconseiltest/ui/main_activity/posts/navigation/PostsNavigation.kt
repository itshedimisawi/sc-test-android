package com.hedimisawi.smartconseiltest.ui.main_activity.posts.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.PostDetailsScreen
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.posts_list.PostsListScreen
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.PostsViewModel

@Composable
fun PostsNavigation(
    navController: NavHostController,
    viewModel: PostsViewModel,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = PostsScreens.PostsListScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = PostsScreens.PostsListScreen.route) {
            PostsListScreen(
                viewModel = viewModel,
                snackbarHostState =  snackbarHostState,
                navController = navController
            )
        }
        composable(route = PostsScreens.PostDetailsScreen.route) {
            PostDetailsScreen(
                viewModel = viewModel,
            )
        }
    }
}