package com.hedimisawi.smartconseiltest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hedimisawi.smartconseiltest.data.models.db.PostWithUserEntity

@Database(entities = [PostWithUserEntity::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun postUserDao(): PostUserDao
}