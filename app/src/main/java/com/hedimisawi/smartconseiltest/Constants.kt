package com.hedimisawi.smartconseiltest

import androidx.annotation.StringRes

const val API_BASE_URL = "https://jsonplaceholder.typicode.com/"
const val SHARED_PREFERENCES_NAME = "MY_SP"


enum class S(@StringRes val resId: Int) {
    ERROR_REGISTRING_USER(R.string.error_registring_user),
    ERROR_AUTHENTICATING_USER(R.string.error_authenticating_user),
    ERROR_EMAIL_ALREADY_IN_USE(R.string.email_already_in_use),
    ERROR_INVALID_EMAIL(R.string.error_invalid_email),
    ERROR_WEAK_PASSWORD(R.string.error_weak_password),
    ERROR_USER_NOT_FOUND(R.string.error_user_not_found),
    ERROR_WRONG_PASSWORD(R.string.error_wrong_password),
    ERROR_LOADING_DATA(R.string.error_loading_data),
    ERROR_LOGGING_OUT(R.string.error_logging_out)
}