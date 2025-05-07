package com.hedimisawi.smartconseiltest.ui.main_activity.account

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.ui.auth_activity.AuthActivity
import com.hedimisawi.smartconseiltest.ui.main_activity.MainUIEvent
import com.hedimisawi.smartconseiltest.ui.shared_components.BlueOutlinedButton


@Composable
fun AccountScreen(modifier: Modifier = Modifier, viewModel: AccountViewModel) {
    val context = LocalContext.current
    val state = viewModel.eventFlow.collectAsState(MainUIEvent.Empty).value

    LaunchedEffect(Unit) {
        viewModel.getCurrentUser()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), contentAlignment = Alignment.Center
    ) {

        if (state is MainUIEvent.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(
                        R.string.logged_in_as,
                        viewModel.currentUser?.displayName ?: stringResource(R.string.unknown)
                    ),
                    textAlign = TextAlign.Center
                )
                BlueOutlinedButton(modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.logout), onClick = {
                    viewModel.logout(onLoggedOut = {
                        context.startActivity(Intent(context, AuthActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        })
                    })
                })
            }
        }
    }
}