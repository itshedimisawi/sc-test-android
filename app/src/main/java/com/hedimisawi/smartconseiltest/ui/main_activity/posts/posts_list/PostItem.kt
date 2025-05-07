package com.hedimisawi.smartconseiltest.ui.main_activity.posts.posts_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hedimisawi.smartconseiltest.R
import com.hedimisawi.smartconseiltest.data.models.PostWithUser
import com.hedimisawi.smartconseiltest.ui.theme.mutedButtonColor

@Composable
fun PostItem(modifier: Modifier = Modifier, post: PostWithUser, onClick: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(id = R.drawable.user__2_), contentDescription = null)
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
            post.title,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(post.body, fontSize = 14.sp, maxLines = 3, overflow = TextOverflow.Ellipsis)
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.post_icon),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
            Text(
                stringResource(R.string.post_id, post.postId),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground.copy(0.6f)
            )
        }
        Button(
            onClick = {
                onClick()
            }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors().copy(
                containerColor = mutedButtonColor, contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = stringResource(R.string.detail))
        }
    }
}