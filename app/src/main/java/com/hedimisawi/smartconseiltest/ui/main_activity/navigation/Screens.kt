package com.hedimisawi.smartconseiltest.ui.main_activity.navigation

import androidx.annotation.StringRes
import com.hedimisawi.smartconseiltest.R

sealed class Screens(@StringRes val name:Int, val route:String){
    object HomeScreen : Screens(R.string.home, "home_screen")
    object PostsScreen : Screens(R.string.posts, "posts_screen")
    object ExploreScreen : Screens(R.string.explore, "explore_screen")
    object AccountScreen : Screens(R.string.account, "account_screen")
}