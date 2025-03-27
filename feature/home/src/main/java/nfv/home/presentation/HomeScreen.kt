package nfv.home.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.R
import nfv.ui_kit.components.inputFields.SearchBarWithFilterButton
import nfv.ui_kit.components.systemBars.BottomBar
import nfv.ui_kit.components.systemBars.BottomBarItemData
import nfv.ui_kit.theme.Danger100
import nfv.ui_kit.theme.Danger200
import nfv.ui_kit.theme.Danger50
import nfv.ui_kit.theme.Danger500
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Info500
import nfv.ui_kit.theme.MainCardShape
import nfv.ui_kit.theme.Primary100
import nfv.ui_kit.theme.Primary200
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.Primary500
import nfv.ui_kit.theme.PromotionCardShape
import nfv.ui_kit.theme.SquareIconShape
import nfv.ui_kit.theme.Success100
import nfv.ui_kit.theme.Success200
import nfv.ui_kit.theme.Success50
import nfv.ui_kit.theme.Success500
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.theme.Warning100
import nfv.ui_kit.theme.Warning200
import nfv.ui_kit.theme.Warning50
import nfv.ui_kit.theme.Warning500
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun HomeScreen(
    state: HomeState,
    onUiEvent: (HomeEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .background(Color.Green)
            .systemBarsPadding(),
        topBar = {
            HomeTopBar(username = state.username)
        },
        bottomBar = {
            HomeBottomBar(
                onClickHome = {
                    onUiEvent(HomeEvent.GoToHome)
                },
                onClickHistory = {
                    onUiEvent(HomeEvent.GoToHistory)
                },
                onClickProfile = {
                    onUiEvent(HomeEvent.GoToProfile)
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(Modifier.height(8.dp))
            SearchBarWithFilterButton(
                modifier = Modifier.padding(16.dp),
                hintText = stringResource(stringR.search_symptoms_diseases),
                text = state.searchText,
                onTextChange = {
                    onUiEvent(HomeEvent.OnSearchTextChanged(it))
                },
                onTextClear = {
                    onUiEvent(HomeEvent.OnSearchTextChanged(""))
                },
                onSearch = {
                    onUiEvent(HomeEvent.OnSearchTextSearched)
                },
                onClickFilterButton = {
                    onUiEvent(HomeEvent.OnSearchFiltered(
                        filter = {
                            //TODO -> fix it
                        }
                    ))
                }
            )

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                listOf(
                    MainCardDto(
                        icon = drawableR.ic_book_appointment,
                        iconColor = Primary500,  //TODO -> bu rengler qaldi
                        iconStrokeColor = Primary200,
                        iconBackgroundColor = Primary100,
                        cardBackgroundColor = Primary50,
                        textHeading = "Book an Appointment",
                        textDescription = "Find a doctor or a specialist",
                        onClick = {

                        }
                    ),
                    MainCardDto(
                        icon = drawableR.ic_qr_scan,
                        iconColor = Success500,
                        iconStrokeColor = Success200,
                        iconBackgroundColor = Success100,
                        cardBackgroundColor = Success50,
                        textHeading = "Appointment with QR",
                        textDescription = "Queuing without the hustle",
                        onClick = {

                        }
                    ),
                    MainCardDto(
                        icon = drawableR.ic_request_consultation,
                        iconColor = Warning500,
                        iconStrokeColor = Warning200,
                        iconBackgroundColor = Warning100,
                        cardBackgroundColor = Warning50,
                        textHeading = "Request consultation",
                        textDescription = "Talk to Specialist",
                        onClick = {

                        }
                    ),
                    MainCardDto(
                        icon = drawableR.ic_locate_pharmacy,
                        iconColor = Danger500,
                        iconStrokeColor = Danger200,
                        iconBackgroundColor = Danger100,
                        cardBackgroundColor = Danger50,
                        textHeading = "Locate a pharmacy",
                        textDescription = "Purchase medicines",
                        onClick = {

                        }
                    )
                ).chunked(2)
                    .forEach { rowColors ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            rowColors.forEach { card ->
                                HomeCard(
                                    modifier = Modifier.weight(1f),
                                    cardItem = card
                                )
                            }

                        }
                    }
            }

            Spacer(Modifier.height(8.dp))

            val promotionCardColors = listOf(
                Primary500,
                Danger500,
                Success500,
                Info500,
                Warning500
            )


            val lazyListState = rememberLazyListState()
            val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                state = lazyListState,
                flingBehavior = snapBehavior
            ) {
                items(count = 5) {
                    PromotionCard(
                        textHeading = "Prevent the spread of COVID-19 Virus",
                        backgroundColor = promotionCardColors[it]
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
        }
    }
}

data class MainCardDto(
    @DrawableRes val icon: Int,
    val iconColor: Color,
    val iconStrokeColor: Color,
    val iconBackgroundColor: Color,
    val cardBackgroundColor: Color,
    val textHeading: String,
    val textDescription: String,
    val onClick: () -> Unit
)

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    cardItem: MainCardDto
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(MainCardShape)
            .background(cardItem.cardBackgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = cardItem.onClick
            )
            .padding(12.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(42.dp)
                .clip(SquareIconShape)
                .background(cardItem.iconBackgroundColor)
                .border(width = 1.dp, color = cardItem.iconStrokeColor, shape = SquareIconShape)
                .padding(5.dp),
            imageVector = ImageVector.vectorResource(cardItem.icon),
            contentDescription = "Promotion card icon",
            tint = cardItem.iconColor
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = cardItem.textHeading,
            style = EDoctorTypography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        //TODO -> burda eslinde height yoxdu, fontun ozunde line height olmalidi ama qoyanmadim tekrar bax typographyde
        Spacer(Modifier.height(8.dp))
        Text(
            text = cardItem.textDescription,
            style = EDoctorTypography.labelMedium.copy(color = Typography500)
        )
    }
}

@Composable
fun PromotionCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textHeading: String
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = screenWidth - 64.dp

    Box(
        modifier = modifier
            .width(cardWidth)
//            .wrapContentHeight()
//            .height(max(cardWidth / 3, IntrinsicSize.Max))
            .height(cardWidth / 3)
            .clip(PromotionCardShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {

                }
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.64f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = textHeading,
                style = EDoctorTypography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Typography50
                )
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "Find out now →",
                style = EDoctorTypography.labelMedium.copy(color = Typography50)
            )
        }
//        Box(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .size(240.dp)
//                .clip(CircleShape)
//                .background(Color.Red)
//                .absoluteOffset(x = 120.dp, y = 16.dp)
//        )
        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxHeight(),
            painter = painterResource(drawableR.img),
            contentDescription = "Promotion card image",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    username: String
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 16.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hi $username!",
                style = EDoctorTypography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            )
            Text(
                text = stringResource(stringR.welcome_message),
                style = EDoctorTypography.labelMedium.copy(color = MaterialTheme.colorScheme.surfaceContainerHighest)
            )
        }
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(8.dp)
                ) //TODO -> bu shadow olmalidi
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(),
                    onClick = {

                    }
                )
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(6.dp)
                .size(20.dp),
            imageVector = ImageVector.vectorResource(drawableR.ic_notifications),
            contentDescription = stringResource(stringR.notifications)
        )
    }
}

@Composable
private fun HomeBottomBar(
    onClickHome: () -> Unit,
    onClickHistory: () -> Unit,
    onClickProfile: () -> Unit
) {
    BottomBar(
        items = listOf(
            BottomBarItemData(
                icon = R.drawable.ic_home_filled,
                label = stringResource(R.string.home),
                onClick = onClickHome
            ),
            BottomBarItemData(
                icon = R.drawable.ic_history_outlined,
                label = stringResource(R.string.history),
                onClick = onClickHistory
            ),
            BottomBarItemData(
                icon = R.drawable.ic_profile_outlined,
                label = stringResource(R.string.profile),
                onClick = onClickProfile
            )
        ),
        selectedItemIndex = 0
    )
}

//@PreviewScreenSizes
//@PreviewFontScale
//@PreviewLightDark
//@PreviewDynamicColors
@Preview(showSystemUi = false)
@Composable
private fun HomeScreenPrev() {
    HomeScreen(
        state = HomeState(
            username = "Natig",
            searchText = ""
        ),
        onUiEvent = {}
    )
}