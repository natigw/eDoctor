package nfv.home.presentation

import nfv.home.domain.NewsResponseData

data class HomeState(
    val username: String,
    val searchText: String,
    val news: List<NewsResponseData>
)