package com.hedimisawi.smartconseiltest.data.models

import com.hedimisawi.smartconseiltest.data.models.db.PostWithUserEntity


data class PostWithUser(
    val postId: Int,
    val userId: Int,
    val userName: String,
    val title: String,
    val body:String
){
    fun toEntity(): PostWithUserEntity {
        return PostWithUserEntity(
            postId = this.postId,
            userId = this.userId,
            userName = this.userName,
            title = this.title,
            body = this.body
        )
    }
}