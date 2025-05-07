package com.hedimisawi.smartconseiltest.ui.main_activity.posts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hedimisawi.smartconseiltest.R

@Composable
fun PostDetailsScreen(modifier: Modifier = Modifier, viewModel: PostsViewModel) {
    viewModel.selectedPost?.let { post ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user__2_),
                    contentDescription = null
                )
                Text(
                    text = post.userName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Text(
                "Title:",
                fontSize = 16.sp,
            )
            Text(
                post.title,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
            )
            HorizontalDivider()
            Text(
                "Body:",
                fontSize = 16.sp,
            )
            Text(
                post.body,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
            )

            HorizontalDivider()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.post_icon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                )
                Text(
                    "POST ID: ${post.postId}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                )

                Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                Icon(
                    painter = painterResource(id = R.drawable.userid_icon),
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                )
                Text(
                    "USER ID: ${post.userId}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
                )
            }
        }
    }
}