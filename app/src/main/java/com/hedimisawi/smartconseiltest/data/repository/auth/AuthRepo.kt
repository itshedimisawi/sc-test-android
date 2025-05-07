package com.hedimisawi.smartconseiltest.data.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.hedimisawi.smartconseiltest.helpers.AuthResource
import com.hedimisawi.smartconseiltest.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepo {
    fun signInWithEmailAndPassword(email: String, password: String): Flow<AuthResource<FirebaseUser?>>
    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        fullName: String
    ): Flow<AuthResource<FirebaseUser?>>
    fun logout(): Flow<AuthResource<Unit>>
    fun getLoggedInUser(): Flow<AuthResource<FirebaseUser?>>
}