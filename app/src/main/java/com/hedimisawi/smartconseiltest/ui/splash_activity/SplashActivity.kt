package com.hedimisawi.smartconseiltest.ui.splash_activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.ui.auth_activity.AuthActivity
import com.hedimisawi.smartconseiltest.ui.main_activity.MainActivity
import com.hedimisawi.smartconseiltest.ui.theme.SmartConseilTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        setContent {
            LaunchedEffect(Unit) {
                viewModel.getCurrentUser(onResult = { isLoggedIn ->
                    val destination = if (isLoggedIn) MainActivity::class.java else AuthActivity::class.java
                    startActivity(Intent(this@SplashActivity, destination).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                })
            }
            SmartConseilTestTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(listOf(Color(0xFF0d4ca9), Color(0xFF1661cd)))
                        )
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.nextdata_logo_blanc),
                            contentDescription = null,
                            Modifier.padding(bottom = 40.dp).fillMaxWidth(0.7f)
                        )
                        AnimatedVisibility(visible = viewModel.isSpinnerVisible) {
                            CircularProgressIndicator(modifier = Modifier.size(60.dp), color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
