package com.hedimisawi.smartconseiltest.ui.auth_activity.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedimisawi.smartconseiltest.S
import com.hedimisawi.smartconseiltest.helpers.AuthError
import com.hedimisawi.smartconseiltest.helpers.AuthResource
import com.hedimisawi.smartconseiltest.data.repository.auth.AuthRepo
import com.hedimisawi.smartconseiltest.ui.auth_activity.AuthUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)

    var isEmailInvalid by mutableStateOf(false)
    var isPasswordTooShort by mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<AuthUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun loginUser(email: String, password: String) {
        isEmailInvalid = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordTooShort = password.length < 6
        if (isEmailInvalid || isPasswordTooShort) return

        CoroutineScope(Dispatchers.IO).launch {
            authRepo.signInWithEmailAndPassword(email, password).collect { result ->
                when (result) {
                    is AuthResource.Loading -> {
                        _eventFlow.emit(AuthUIEvent.Loading)
                    }

                    is AuthResource.Success -> {
                        _eventFlow.emit(AuthUIEvent.Authenticated)
                    }

                    is AuthResource.Error -> {
                        val errorMessage = when (result.error) {
                            AuthError.InvalidEmail -> S.ERROR_INVALID_EMAIL
                            AuthError.UserNotFound -> S.ERROR_USER_NOT_FOUND
                            AuthError.WrongPassword -> S.ERROR_WRONG_PASSWORD
                            else -> S.ERROR_AUTHENTICATING_USER
                        }
                        _eventFlow.emit(AuthUIEvent.ShowSnackbar(message = errorMessage))
                    }
                }
            }
        }
    }

}
