package com.hedimisawi.smartconseiltest.helpers

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

sealed class AuthError {
    object InvalidEmail : AuthError()
    object WeakPassword : AuthError()
    object EmailAlreadyInUse : AuthError()
    object WrongPassword : AuthError()
    object UserNotFound : AuthError()
    object NotLoggedIn : AuthError()
    object Unknown : AuthError()
}
sealed class AuthResource<T>(val data: T? = null, val error:AuthError? = null) {
    class Loading<T>(data: T? = null) : AuthResource<T>(data)
    class Success<T>(data: T?) : AuthResource<T>(data)
    class Error<T>(error: AuthError, data: T? = null) : AuthResource<T>(data, error)
}