package com.hedimisawi.smartconseiltest.data.repository.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
import com.hedimisawi.smartconseiltest.helpers.AuthError
import com.hedimisawi.smartconseiltest.helpers.AuthResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) : AuthRepo {
    override fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        fullName: String
    ): Flow<AuthResource<FirebaseUser?>> = flow {
        emit(AuthResource.Loading())
        try {
            val authResult = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()

            val user = authResult.user

            user?.updateProfile(
                userProfileChangeRequest {
                    displayName = fullName
                }
            )?.await()

            emit(AuthResource.Success(user))
        } catch (e: FirebaseAuthUserCollisionException) {
            emit(AuthResource.Error(AuthError.EmailAlreadyInUse))
        } catch (e: FirebaseAuthWeakPasswordException) {
            emit(AuthResource.Error(AuthError.WeakPassword))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(AuthResource.Error(AuthError.InvalidEmail))
        } catch (e: Exception) {
            emit(AuthResource.Error(AuthError.Unknown))
        }
    }

    override fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthResource<FirebaseUser?>> = flow {
        emit(AuthResource.Loading())
        try {
            val authResult = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()

            emit(AuthResource.Success(authResult.user))
        } catch (e: FirebaseAuthInvalidUserException) {
            emit(AuthResource.Error(AuthError.UserNotFound))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            emit(AuthResource.Error(AuthError.WrongPassword))
        } catch (e: Exception) {
            emit(AuthResource.Error(AuthError.Unknown))
        }
    }

    override fun logout(): Flow<AuthResource<Unit>> = flow {
        emit(AuthResource.Loading())
        try {
            firebaseAuth.signOut()
            emit(AuthResource.Success(Unit))
        } catch (e: IllegalStateException) {
            emit(AuthResource.Error(AuthError.NotLoggedIn))
        } catch (e: Exception) {
            emit(AuthResource.Error(AuthError.Unknown))
        }
    }
    override fun getLoggedInUser(): Flow<AuthResource<FirebaseUser?>> = flow {
        emit(AuthResource.Loading())
        try {
            val currentUser = firebaseAuth.currentUser
            if (currentUser != null) {
                emit(AuthResource.Success(currentUser))
            } else {
                emit(AuthResource.Error(AuthError.NotLoggedIn))
            }
        } catch (e: Exception) {
            emit(AuthResource.Error(AuthError.Unknown))
        }
    }

}