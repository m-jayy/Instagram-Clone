package com.mjay.instagram_clone.screens.search


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjay.instagram_clone.R
import com.mjay.instagram_clone.models.Post
import com.mjay.instagram_clone.models.SearchFilter
import com.mjay.instagram_clone.repositories.PostRepository
import com.mjay.instagram_clone.sealed.DataResponse
import com.mjay.instagram_clone.sealed.Error
import com.mjay.instagram_clone.sealed.UiState
import com.mjay.instagram_clone.utils.appendOrRemove
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A View model with hiltViewModel annotation that is used to access this view model everywhere needed
 */
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val postRepository: PostRepository,
) : ViewModel() {
    val lastSearchQuery = mutableStateOf("")
    val filters = listOf(
        SearchFilter(
            id = 1,
            icon = R.drawable.ic_tv,
            name = R.string.igtv,
        ),
        SearchFilter(
            id = 2,
            icon = R.drawable.ic_shop_bag,
            name = R.string.shop,
        ),
        SearchFilter(
            id = 3,
            name = R.string.style,
        ),
        SearchFilter(
            id = 4,
            name = R.string.sports,
        ),
        SearchFilter(
            id = 5,
            name = R.string.auto,
        ),
        SearchFilter(
            id = 6,
            name = R.string.anime,
        ),
        SearchFilter(
            id = 7,
            name = R.string.culture,
        ),
        SearchFilter(
            id = 8,
            name = R.string.nature,
        ),
    )
    val activeFilters: MutableList<Int> = mutableStateListOf()
    val trendingPosts: MutableList<Post> = mutableStateListOf()
    val trendingPostsUiState = mutableStateOf<UiState>(UiState.Loading)

    init {
        getTrendingPosts()
    }

    /** A function to update the query that the user is currently typing into the input field "the state" */
    fun updateSearchQuery(query: String) {
        this.lastSearchQuery.value = query
    }

    /** The function that actually make the search process */
    fun searchForPosts(query: String) {

    }

    fun updateActiveFilters(id: Int) {
        activeFilters.appendOrRemove(element = id)
    }

    private fun getTrendingPosts() {
        trendingPostsUiState.value = UiState.Loading
        viewModelScope.launch {
            postRepository.getFakePosts().let { dataResponse ->
                when (dataResponse) {
                    is DataResponse.Success -> {
                        /** Fetched successfully */
                        trendingPostsUiState.value = UiState.Success
                        dataResponse.data?.let {
                            trendingPosts.addAll(it)
                        }
                    }
                    else -> {
                        /** Fetching failed unfortunately */
                        trendingPostsUiState.value =
                            UiState.Error(error = dataResponse.error ?: Error.Network)
                    }
                }
            }
        }
    }
}