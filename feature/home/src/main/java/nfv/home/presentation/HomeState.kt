package nfv.home.presentation

import nfv.home.NewsResponse

data class HomeState(
    val username: String,
    val searchText: String,
    val news: List<NewsResponse>
)