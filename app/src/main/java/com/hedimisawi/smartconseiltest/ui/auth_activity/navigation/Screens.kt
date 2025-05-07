package com.hedimisawi.smartconseiltest.ui.auth_activity.navigation

import androidx.annotation.StringRes
import com.hedimisawi.smartconseiltest.R

sealed class Screens(@StringRes val name:Int, val route:String){
    object LoginScreen : Screens(R.string.login, "login_screen")
    object RegisterScreen : Screens(R.string.register, "register_screen")
}