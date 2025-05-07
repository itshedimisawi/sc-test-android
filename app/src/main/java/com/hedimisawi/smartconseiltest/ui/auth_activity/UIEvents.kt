package com.hedimisawi.smartconseiltest.ui.auth_activity

import com.hedimisawi.smartconseiltest.S


sealed class AuthUIEvent {
    data class ShowSnackbar(val message: S) : AuthUIEvent()
    data object Loading : AuthUIEvent()
    data object Authenticated : AuthUIEvent()
    data object Empty : AuthUIEvent()
}
