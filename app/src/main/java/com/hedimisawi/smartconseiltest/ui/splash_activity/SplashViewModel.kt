package com.hedimisawi.smartconseiltest.ui.splash_activity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedimisawi.smartconseiltest.helpers.AuthResource
import com.hedimisawi.smartconseiltest.helpers.Resource
import com.hedimisawi.smartconseiltest.data.repository.auth.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepo: AuthRepo
) : ViewModel() {
    var isSpinnerVisible by mutableStateOf(false)
    fun getCurrentUser(onResult: (Boolean) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            authRepo.getLoggedInUser().collect { result ->
                delay(2000L)
                when (result) {
                    is AuthResource.Error -> {
                        isSpinnerVisible = false
                        onResult(false)
                    }

                    is AuthResource.Success -> {
                        isSpinnerVisible = false
                        onResult(result.data != null)

                    }

                    is AuthResource.Loading -> {
                        isSpinnerVisible = true

                    }
                }
            }
        }
    }
}