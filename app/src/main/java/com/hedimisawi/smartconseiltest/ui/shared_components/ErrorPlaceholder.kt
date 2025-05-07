package com.hedimisawi.smartconseiltest.ui.shared_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.ui.theme.errorMessageColor

@Composable
fun ErrorPlaceholder(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.background)
            .border(
                1.dp,
                MaterialTheme.colorScheme.onBackground.copy(0.5f),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.server_error),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            stringResource(R.string.server_error_code_5xx),
            color = errorMessageColor,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}