package com.hedimisawi.smartconseiltest.ui.main_activity

import com.hedimisawi.smartconseiltest.S


sealed class MainUIEvent {
    data class ShowSnackbar(val message: S) : MainUIEvent()
    data object Loading : MainUIEvent()
    data object Empty : MainUIEvent()
}
