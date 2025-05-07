package com.hedimisawi.smartconseiltest.ui.main_activity.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.hedimisawi.smartconseiltest.S
import com.hedimisawi.smartconseiltest.data.repository.auth.AuthRepo
import com.hedimisawi.smartconseiltest.helpers.AuthResource
import com.hedimisawi.smartconseiltest.ui.main_activity.MainUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {
    var currentUser by mutableStateOf<FirebaseUser?>(null)

    private val _eventFlow = MutableSharedFlow<MainUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun getCurrentUser(){
        CoroutineScope(Dispatchers.IO).launch {
            authRepo.getLoggedInUser().collect { result ->
                when (result) {
                    is AuthResource.Error -> {
                        _eventFlow.emit(MainUIEvent.ShowSnackbar(S.ERROR_LOADING_DATA))
                    }

                    is AuthResource.Success -> {
                        currentUser = result.data
                        _eventFlow.emit(MainUIEvent.Empty)
                    }

                    is AuthResource.Loading -> {
                        _eventFlow.emit(MainUIEvent.Loading)
                    }
                }
            }
        }
    }

    fun logout(onLoggedOut: ()->Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            authRepo.logout().collect { result ->
                when (result) {
                    is AuthResource.Error -> {
                        _eventFlow.emit(MainUIEvent.ShowSnackbar(S.ERROR_LOGGING_OUT))
                    }

                    is AuthResource.Success -> {
                        onLoggedOut()
                    }

                    is AuthResource.Loading -> {
                        _eventFlow.emit(MainUIEvent.Loading)
                    }
                }
            }
        }
    }
}