package com.hedimisawi.smartconseiltest.data.models.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hedimisawi.smartconseiltest.data.models.PostWithUser

@Entity(tableName = "PostWithUserEntity")
data class PostWithUserEntity(
    @PrimaryKey val postId: Int,
    val userId: Int,
    val userName: String,
    val title: String,
    val body: String
){
    fun toDataClass(): PostWithUser {
        return PostWithUser(
            postId = this.postId,
            userId = this.userId,
            userName = this.userName,
            title = this.title,
            body = this.body
        )
    }
}
