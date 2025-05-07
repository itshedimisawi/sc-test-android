package com.hedimisawi.smartconseiltest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hedimisawi.smartconseiltest.data.models.db.PostWithUserEntity

@Dao
interface PostUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostWithUserEntity>)

    @Query("SELECT * FROM PostWithUserEntity")
    suspend fun getAllPosts(): List<PostWithUserEntity>

    @Query("DELETE FROM PostWithUserEntity")
    suspend fun clearAllPosts()
}