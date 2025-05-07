package com.hedimisawi.smartconseiltest.ui.auth_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.hedimisawi.smartconseiltest.ui.auth_activity.login.LoginViewModel
import com.hedimisawi.smartconseiltest.ui.auth_activity.navigation.AuthNavigation
import com.hedimisawi.smartconseiltest.ui.main_activity.MainActivity
import com.hedimisawi.smartconseiltest.ui.theme.SmartConseilTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartConseilTestTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                }) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        AuthNavigation(navController = navController,
                            onAuthenticated = {
                                startActivity(Intent(this@AuthActivity, MainActivity::class.java ).apply {
                                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                })
                            },
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }
}
