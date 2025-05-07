package com.hedimisawi.smartconseiltest.ui.main_activity.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hedimisawi.smartconseiltest.S
import com.hedimisawi.smartconseiltest.helpers.Resource
import com.hedimisawi.smartconseiltest.data.models.PostWithUser
import com.hedimisawi.smartconseiltest.data.repository.posts.PostsRepo
import com.hedimisawi.smartconseiltest.ui.main_activity.MainUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepo: PostsRepo
) : ViewModel() {
    var posts = mutableStateListOf<PostWithUser>()
    var selectedPost by mutableStateOf<PostWithUser?>(null)
    var searchQuery by mutableStateOf("")

    private val _eventFlow = MutableSharedFlow<MainUIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()


    var isError by mutableStateOf(false)


    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            postsRepo.getPostsWithUsers().collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _eventFlow.emit(MainUIEvent.Loading)
                    }

                    is Resource.Success -> {
                        val mapped = result.data ?: emptyList()
                        posts.clear()
                        posts.addAll(mapped)

                        _eventFlow.emit(MainUIEvent.Empty)
                    }

                    is Resource.Error -> {
                        isError = true
                        _eventFlow.emit(MainUIEvent.ShowSnackbar(S.ERROR_LOADING_DATA))
                    }
                }
            }
        }
    }
}
