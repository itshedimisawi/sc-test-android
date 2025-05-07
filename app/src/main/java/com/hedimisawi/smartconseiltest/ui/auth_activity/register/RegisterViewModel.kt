package com.hedimisawi.smartconseiltest.ui.auth_activity.register

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
class RegisterViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
    var isPasswordVisible by mutableStateOf(false)
    var isPasswordNotMatching by mutableStateOf(false)
    var isEmailInvalid by mutableStateOf(false)
    var isPasswordTooShort by mutableStateOf(false)
    var isNameEmpty by mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<AuthUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()
    fun registerUser() {
        isEmailInvalid = !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordTooShort = password.length < 6
        isPasswordNotMatching = password != confirmPassword
        isNameEmpty = name.isBlank()

        if (isEmailInvalid || isPasswordTooShort || isPasswordNotMatching || isNameEmpty) {
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            authRepo.signUpWithEmailAndPassword(
                email = email, password = password, fullName = name
            ).collect { result ->
                when (result) {
                    is AuthResource.Loading -> {
                        _eventFlow.emit(AuthUIEvent.Loading)
                    }

                    is AuthResource.Success -> {
                        _eventFlow.emit(AuthUIEvent.Authenticated)
                    }

                    is AuthResource.Error -> {
                        val message = when (result.error) {
                            AuthError.EmailAlreadyInUse -> S.ERROR_EMAIL_ALREADY_IN_USE
                            AuthError.InvalidEmail -> S.ERROR_INVALID_EMAIL
                            AuthError.WeakPassword -> S.ERROR_WEAK_PASSWORD
                            else -> S.ERROR_REGISTRING_USER
                        }
                        _eventFlow.emit(AuthUIEvent.ShowSnackbar(message))
                    }
                }
            }
        }
    }

}