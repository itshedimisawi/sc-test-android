package com.hedimisawi.smartconseiltest.data.repository.posts

import com.hedimisawi.smartconseiltest.helpers.Resource
import com.hedimisawi.smartconseiltest.data.models.PostWithUser
import com.hedimisawi.smartconseiltest.data.models.dtos.Post
import com.hedimisawi.smartconseiltest.data.models.dtos.User
import kotlinx.coroutines.flow.Flow

interface PostsRepo {
    fun getPostsWithUsers(): Flow<Resource<List<PostWithUser>>>
}