package com.hedimisawi.smartconseiltest.data.repository.posts

import com.hedimisawi.smartconseiltest.data.local.PostUserDao
import com.hedimisawi.smartconseiltest.data.models.PostWithUser
import com.hedimisawi.smartconseiltest.data.models.db.PostWithUserEntity
import com.hedimisawi.smartconseiltest.data.network.APIService
import com.hedimisawi.smartconseiltest.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepoImpl @Inject constructor(
    private val apiService: APIService, private val dao: PostUserDao
) : PostsRepo {

    override fun getPostsWithUsers(): Flow<Resource<List<PostWithUser>>> = flow {
        emit(Resource.Loading())

        try {
            val postsResponse = apiService.getPosts().execute()
            val usersResponse = apiService.getUsers().execute()

            if (postsResponse.isSuccessful && usersResponse.isSuccessful) {
                val posts = postsResponse.body().orEmpty()
                val users = usersResponse.body().orEmpty()

                val combined = posts.map { post ->
                    PostWithUser(
                        postId = post.id ?: 0,
                        userId = post.userId ?: 0,
                        userName = users.find { it.id == post.userId }?.name.orEmpty(),
                        title = post.title.orEmpty(),
                        body = post.body.orEmpty()
                    )
                }

                dao.clearAllPosts()
                dao.insertPosts(combined.map {
                    PostWithUserEntity(
                        postId = it.postId,
                        userId = it.userId,
                        userName = it.userName,
                        title = it.title,
                        body = it.body
                    )
                })

                emit(Resource.Success(combined))
            } else {
                val cached = dao.getAllPosts()
                if (cached.isNotEmpty()) {
                    emit(Resource.Success(cached.map { it.toDataClass() }))
                } else {
                    emit(Resource.Error("Server error"))
                }
            }
        } catch (e: Exception) {
            val cached = dao.getAllPosts()
            if (cached.isNotEmpty()) {
                emit(Resource.Success(cached.map { it.toDataClass() }))
            } else {
                emit(Resource.Error("Network error"))
            }
        }
    }
}
