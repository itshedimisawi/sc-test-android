package com.hedimisawi.smartconseiltest.ui.auth_activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hedimisawi.smartconseiltest.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.nextdata_logo),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(0.6f)
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(text = stringResource(R.string.welcome_to_nextdata), fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            text = stringResource(R.string.login_with_email),
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
        )
    }

}