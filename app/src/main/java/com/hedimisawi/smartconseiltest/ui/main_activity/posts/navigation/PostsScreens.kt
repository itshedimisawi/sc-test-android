package com.hedimisawi.smartconseiltest.ui.main_activity.posts.navigation


sealed class PostsScreens(val route:String){
    object PostsListScreen : PostsScreens("posts_list_screen")
    object PostDetailsScreen : PostsScreens("posts_details_screen")
}