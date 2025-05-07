package com.hedimisawi.smartconseiltest.ui.auth_activity.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hedimisawi.smartconseiltest.ui.auth_activity.login.LoginScreen
import com.hedimisawi.smartconseiltest.ui.auth_activity.login.LoginViewModel
import com.hedimisawi.smartconseiltest.ui.auth_activity.register.RegisterScreen
import com.hedimisawi.smartconseiltest.ui.auth_activity.register.RegisterViewModel


@Composable
fun AuthNavigation(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    onAuthenticated: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = Screens.LoginScreen.route) {
            LoginScreen(
                viewModel = hiltViewModel<LoginViewModel>(),
                onAuthenticated = onAuthenticated,
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }

        composable(route = Screens.RegisterScreen.route) {
            RegisterScreen(
                viewModel = hiltViewModel<RegisterViewModel>(),
                onAuthenticated = onAuthenticated,
                navController = navController,
                snackbarHostState = snackbarHostState
            )
        }
    }
}