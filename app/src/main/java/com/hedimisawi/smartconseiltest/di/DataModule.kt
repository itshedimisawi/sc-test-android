package com.hedimisawi.smartconseiltest.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.hedimisawi.smartconseiltest.SHARED_PREFERENCES_NAME
import com.hedimisawi.smartconseiltest.data.local.AppDB
import com.hedimisawi.smartconseiltest.data.local.PostUserDao
import com.hedimisawi.smartconseiltest.data.network.APIService
import com.hedimisawi.smartconseiltest.data.repository.auth.AuthRepo
import com.hedimisawi.smartconseiltest.data.repository.auth.AuthRepoImpl
import com.hedimisawi.smartconseiltest.data.repository.posts.PostsRepo
import com.hedimisawi.smartconseiltest.data.repository.posts.PostsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    @Named("preferences")
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesAuthRepository (firebaseAuth: FirebaseAuth) : AuthRepo {
        return AuthRepoImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesPostsRepository (apiService: APIService, dao: PostUserDao) : PostsRepo {
        return PostsRepoImpl(apiService, dao)
    }



    // Room stuff

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(
            context,
            AppDB::class.java,
            "posts_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesPostUserDao(db: AppDB): PostUserDao {
        return db.postUserDao()
    }
}