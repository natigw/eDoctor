package nfv.home

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.theme.Danger50
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Gray300
import nfv.ui_kit.theme.Gray500
import nfv.ui_kit.theme.MainCardShape
import nfv.ui_kit.theme.Primary50
import nfv.ui_kit.theme.PromotionCardShape
import nfv.ui_kit.theme.Success50
import nfv.ui_kit.theme.Typography50
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.theme.Typography700
import nfv.ui_kit.theme.Warning50
import nfv.ui_kit.R.drawable as drawableR

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.background(Color.White)) {
        TopBar()
        HomeSearchBar()

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf(
                MainCardDto(
                    backgroundColor = Primary50,
                    icon = drawableR.img_2,
                    textHeading = "Book an Appointment",
                    textDescription = "Find a doctor or a specialist",
                    onClick = {

                    }
                ),
                MainCardDto(
                    backgroundColor = Success50,
                    icon = drawableR.img_2,
                    textHeading = "Appointment with QR",
                    textDescription = "Queuing without the hustle",
                    onClick = {

                    }
                ),
                MainCardDto(
                    backgroundColor = Warning50,
                    icon = drawableR.img_2,
                    textHeading = "Request consultation",
                    textDescription = "Talk to Specialist",
                    onClick = {

                    }
                ),
                MainCardDto(
                    backgroundColor = Danger50,
                    icon = drawableR.img_2,
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
                                card
                            )
                        }

                    }
                }
        }

        Spacer(Modifier.height(8.dp))

        val promotionCardColors = listOf(
            Color(0xFF254EDB),
            Color(0xFFF04438),
            Color(0xFF16B364),
            Color(0xFFCED2D9),
            Color(0xFFEF6820)
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


        val bottomIcons = listOf(
            drawableR.img_7,
            drawableR.img_8,
            drawableR.img_9,
            drawableR.img_12,
            drawableR.img_10
        )
        val bottomTexts = listOf(
            "Home",
            "Calendar",
            "History",
            "Chat",
            "Account"
        )

        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val itemWidth = screenWidth / 5

        Spacer(Modifier.height(8.dp))

        LazyRow(
            modifier = Modifier.padding(top = 12.dp)
        ) {
            items(count = 5) {
                BottomBarItem(
                    modifier = Modifier.width(itemWidth),
                    icon = bottomIcons[it],
                    text = bottomTexts[it],
                    isSelected = it == 0
                )
            }
        }
    }
}

data class MainCardDto(
    @DrawableRes val icon: Int,
    val backgroundColor: Color,
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
            .background(cardItem.backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                onClick = cardItem.onClick
            )
            .padding(12.dp)
    ) {
        Image(
            modifier = Modifier.size(42.dp),
            painter = painterResource(cardItem.icon),
            contentDescription = "Promotion card image",
            contentScale = ContentScale.Crop
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
fun TopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Hi Natig!",
                style = EDoctorTypography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "May you always in a good condition",
                style = EDoctorTypography.labelMedium.copy(color = Typography700)
            )
        }
        Spacer(Modifier.width(8.dp))
        Image(
            modifier = Modifier.size(42.dp),
            painter = painterResource(drawableR.img_2),
            contentDescription = "Promotion card image",
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BottomBarItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    isSelected: Boolean = false
) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(icon),
            contentDescription = "bottom nav icon",
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = text,
            color = if (isSelected) Color(0xFF254EDB) else Color(0xFFA1A1AA)
        )
    }
}

@Composable
fun HomeSearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    onClick = {

                    }
                )
                .border(width = 1.dp, color = Gray300, shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_search),
                contentDescription = "Search",
                tint = Gray500
            )
            Spacer(Modifier.width(12.dp))
            Text(
                text = "symptoms, diseases...",
                style = EDoctorTypography.bodyMedium.copy(color = Typography500)
            )
        }
        Spacer(Modifier.width(8.dp))
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(drawableR.img_2),
            contentDescription = "null",
            contentScale = ContentScale.Crop
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
            .height(cardWidth / 3)
            .clip(PromotionCardShape)
            .background(backgroundColor)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
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
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.BottomEnd),
            painter = painterResource(drawableR.img_2),
            contentDescription = "Promotion card image",
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPrev() {
    HomeScreen()
}