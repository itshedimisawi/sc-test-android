package com.hedimisawi.smartconseiltest.ui.main_activity.posts.posts_list

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hedimisawi.smartconseiltest.ui.main_activity.MainUIEvent
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.PostsViewModel
import com.hedimisawi.smartconseiltest.ui.main_activity.posts.navigation.PostsScreens
import kotlinx.coroutines.launch

@Composable
fun PostsListScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel,
    navController: NavController,
    snackbarHostState: SnackbarHostState
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.eventFlow.collectAsState(MainUIEvent.Empty).value

    val isLoading = state is MainUIEvent.Loading
    LaunchedEffect(state) {
        when (state) {
            is MainUIEvent.Loading -> {}
            is MainUIEvent.Empty -> {}
            is MainUIEvent.ShowSnackbar -> {
                scope.launch {
                    snackbarHostState.showSnackbar(context.getString(state.message.resId))
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    Column(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar(modifier = Modifier.fillMaxWidth(),
                value = viewModel.searchQuery,
                onValueChange = { viewModel.searchQuery = it })
        }
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                ) {
                    itemsIndexed(items = if(viewModel.searchQuery.trim() == "") viewModel.posts else viewModel.posts.filter {
                        it.title.contains(viewModel.searchQuery.trim()) or it.body.contains(viewModel.searchQuery.trim())
                    }) { _, post ->
                        PostItem(modifier = Modifier.padding(vertical = 8.dp),
                            post = post,
                            onClick = {
                                viewModel.selectedPost = post
                                navController.navigate(PostsScreens.PostDetailsScreen.route)
                            })
                    }
                }
            }
        }

    }
}
