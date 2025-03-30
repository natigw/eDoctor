package nfv.auth.presentation.screens.onboard

import androidx.annotation.DrawableRes

data class OnBoardState(
    val pages: List<OnBoardPage>,
    val currentPage: Int   //0,1,2,...
)

data class OnBoardPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
)