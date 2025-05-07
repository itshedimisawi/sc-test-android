package com.hedimisawi.smartconseiltest.ui.main_activity.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hedimisawi.smartconseiltest.ui.main_activity.account.AccountScreen
import com.hedimisawi.smartconseiltest.ui.main_activity.account.AccountViewModel
import com.hedimisawi.smartconseiltest.ui.main_activity.discover.ExploreScreen
import com.hedimisawi.smartconseiltest.ui.main_activity.home.HomeScreen
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.PostsScreen
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.PostsViewModel


@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen()

        }

        composable(route = Screens.PostsScreen.route) {
            PostsScreen(
                viewModel = hiltViewModel<PostsViewModel>(),
            )
        }

        composable(route = Screens.ExploreScreen.route) {
            ExploreScreen()
        }

        composable(route = Screens.AccountScreen.route) {
            AccountScreen(viewModel = hiltViewModel<AccountViewModel>())
        }
    }
}