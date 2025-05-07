package com.hedimisawi.smartconseiltest.ui.main_activity.account

import androidx.lifecycle.ViewModel
import com.hedimisawi.smartconseiltest.data.repository.auth.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {

}