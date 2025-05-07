package com.hedimisawi.smartconseiltest.ui.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.ui.main_activity.navigation.BottomBar
import com.hedimisawi.smartconseiltest.ui.main_activity.navigation.BottomBarItem
import com.hedimisawi.smartconseiltest.ui.main_activity.navigation.Navigation
import com.hedimisawi.smartconseiltest.ui.main_activity.navigation.Screens
import com.hedimisawi.smartconseiltest.ui.main_activity.navigation.TopBar
import com.hedimisawi.smartconseiltest.ui.theme.SmartConseilTestTheme
import com.hedimisawi.smartconseiltest.ui.theme.backgroundBlue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartConseilTestTheme {
                val navController = rememberNavController()
                val selectedRoute = navController.currentBackStackEntryAsState().value?.destination
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopBar(title =  when(selectedRoute?.route){
                            Screens.AccountScreen.route -> stringResource(id = Screens.AccountScreen.name)
                            Screens.HomeScreen.route -> stringResource(id = Screens.HomeScreen.name)
                            Screens.ExploreScreen.route -> stringResource(id = Screens.ExploreScreen.name)
                            Screens.PostsScreen.route -> stringResource(id = Screens.PostsScreen.name)
                            else -> ""
                        },
                            onMenuPressed = {})
                    },
                    bottomBar = {
                    BottomBar(
                        selectedRoute = selectedRoute?.route,
                        onSelected = { route ->
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        items = listOf(
                            BottomBarItem(
                                icon = R.drawable.home,
                                label = "Home",
                                route = Screens.HomeScreen.route
                            ),
                            BottomBarItem(
                                icon = R.drawable.posts,
                                label = "Posts",
                                route = Screens.PostsScreen.route
                            ),
                            BottomBarItem(
                                icon = R.drawable.explore,
                                label = "Explore",
                                route = Screens.ExploreScreen.route
                            ),
                            BottomBarItem(
                                icon = R.drawable.account,
                                label = "Account",
                                route = Screens.AccountScreen.route
                            ),
                        )
                    )
                }) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundBlue)
                            .padding(innerPadding)
                    ) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}
