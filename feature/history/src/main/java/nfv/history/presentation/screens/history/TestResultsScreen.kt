package nfv.history.presentation.screens.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nfv.ui_kit.components.inputFields.SearchBarWithFilterButton
import nfv.ui_kit.components.systemBars.BottomBar
import nfv.ui_kit.components.systemBars.BottomBarItemData
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.BaseTransparent
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Info50
import nfv.ui_kit.theme.Primary900
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.theme.Typography900
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun TestResultsScreen(
    state: HistoryState,
    onUiEvent: (HistoryEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            HistoryTopBar(
                state = state,
                onUiEvent = onUiEvent
            )
        },
        bottomBar = {
            HistoryBottomBar(
                state = state,
                onUiEvent = onUiEvent
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(innerPadding)
        ) {
            ResultListByMonth(
                state = state,
                onReadStatusChanged = {
                    onUiEvent(HistoryEvent.OnReadStatusChanged(it))
                },
                onClickDownloadDocument = { url, title ->
                    onUiEvent(
                        HistoryEvent.OnClickDownloadDocument(
                            link = url,
                            title = title
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun HistoryTopBar(
    state: HistoryState,
    onUiEvent: (HistoryEvent) -> Unit
) {
    var isSearchClicked by remember { mutableStateOf(false) }
    Column {
        TopBar(
            headerText = stringResource(stringR.header_test_results),
            leadingIcon = if (false)//state.isComingFromProfile)
                IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(HistoryEvent.OnNavigateBack)
                    }
                )
            else null,
            trailingIcon = IconWithAction(
                icon = drawableR.ic_search,
                action = {
                    isSearchClicked = !isSearchClicked
                }
            )
        )

        AnimatedVisibility(
            visible = isSearchClicked
        ) {
            SearchBarWithFilterButton(
                modifier = Modifier.padding(16.dp),
                hintText = stringResource(stringR.search_for_result),
                text = state.searchText,
                onTextChange = {
                    onUiEvent(HistoryEvent.OnSearchTextChanged(it))
                },
                onTextClear = {
                    onUiEvent(HistoryEvent.OnSearchTextChanged(""))
                    onUiEvent(HistoryEvent.OnSearchTextSearched(""))
                },
                onSearch = {
                    onUiEvent(HistoryEvent.OnSearchTextSearched(state.searchText))
                },
                onClickFilterButton = {
                    //TODO
                }
            )
        }
    }
}

@Composable
private fun HistoryBottomBar(
    state: HistoryState,
    onUiEvent: (HistoryEvent) -> Unit
) {
    if (true)//isComingFromProfile.not())
        BottomBar(
            items = listOf(
                BottomBarItemData(
                    icon = drawableR.ic_home_outlined,
                    label = stringResource(stringR.home),
                    onClick = {
                        onUiEvent(HistoryEvent.GoToHome)
                    }
                ),
                BottomBarItemData(
                    icon = drawableR.ic_history_filled,
                    label = stringResource(stringR.history),
                    onClick = {
                        onUiEvent(HistoryEvent.GoToHistory)
                    }
                ),
                BottomBarItemData(
                    icon = drawableR.ic_profile_outlined,
                    label = stringResource(stringR.profile),
                    onClick = {
                        onUiEvent(HistoryEvent.GoToProfile)
                    }
                )
            ),
            selectedItemIndex = 1
        )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultListByMonth(
    state: HistoryState,
    onReadStatusChanged: (String) -> Unit,
    onClickDownloadDocument: (String, String) -> Unit
) {
    LazyColumn {
        state.filteredTestResults.forEach { (date, resultList) ->
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0.75f to BaseWhite,
                                    1f to BaseTransparent
                                )
                            )
                        )
                        .padding(top = 4.dp, bottom = 12.dp),
                    textAlign = TextAlign.Center,
                    text = date, //.month
                    style = EDoctorTypography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Primary900
                    )
                )
            }

            items(resultList.size) { testResult ->

                var isExpanded by remember { mutableStateOf(false) }
                val rotationAngle by animateFloatAsState(
                    targetValue = if (isExpanded) 90f else 0f,
                    animationSpec = tween(durationMillis = 300)
                )

                Column {
                    Row(
                        modifier = Modifier
                            .background(if (resultList[testResult].isRead) BaseTransparent else Info50)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(),
                                onClick = {
                                    isExpanded = !isExpanded
                                    onReadStatusChanged(resultList[testResult].id)
                                }
                            )
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .animateContentSize()
                        ) {
                            Text(
                                text = resultList[testResult].testTitle,
                                style = EDoctorTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(4.dp))
                            if (isExpanded) {
                                Text(
                                    text = resultList[testResult].testDescription,
                                    style = EDoctorTypography.labelMedium.copy(color = Typography500)
                                )
                                Spacer(Modifier.height(12.dp))
                                Text(
                                    text = stringResource(stringR.lab_name) + resultList[testResult].labName,
                                    style = EDoctorTypography.labelMedium.copy(color = Typography500),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = stringResource(stringR.test_date) + resultList[testResult].testDate,
                                    style = EDoctorTypography.labelMedium.copy(color = Typography500),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height(4.dp))
                                Row(
                                    modifier = Modifier
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = ripple(),
                                            onClick = {
                                                onClickDownloadDocument(
                                                    resultList[testResult].testFileUrl,
                                                    resultList[testResult].testTitle
                                                )
                                            }
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        modifier = Modifier.size(16.dp),
                                        imageVector = ImageVector.vectorResource(drawableR.ic_download_file_outlined),
                                        contentDescription = stringResource(stringR.download_pdf),
                                        tint = Typography500
                                    )
                                    Text(
                                        text = " download pdf", //TODO -> bunu button component ile evez et
                                        style = EDoctorTypography.labelMedium.copy(color = Typography500),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            } else {
                                Text(
                                    text = "${resultList[testResult].testDate} • ${resultList[testResult].labName}",
                                    style = EDoctorTypography.labelMedium.copy(color = Typography500),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Icon(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .rotate(rotationAngle),
                            imageVector = ImageVector.vectorResource(drawableR.ic_arrow_right),
                            contentDescription = stringResource(stringR.details_test_result),
                            tint = Typography900
                        )
                    }
                    if (testResult == resultList.size - 1)
                        Spacer(Modifier.height(8.dp))
                    else
                        HorizontalDivider(color = Info50)
                }
            }
        }
    }
}

//data class TestResultDescriptionItem(
//    val resultId: Int,
//    val patientId: Int,
//    val title: String,
//    val description: String,
//    val result: Double, //TODO-> bunu string etmek yaxsidi? cunki bezi neticeler 50mq/l, bezileri ela pis ola biler
//    val previousResult: Double?,
//    val measure: String, //TODO -> bu enum olmalidi??
//    val referenceInterval: ReferenceInterval,
//    val labName: String,
//    val testDate: String,  //Date TODO bunu date etmek lazimdi?
//    val resultDate: String //Date TODO bunu date etmek lazimdi?
//)
//
//data class PatientDetails(
//    val id: Int,
//    val fullName: String,
//    val sex: Sex,
//    val birthDate: Date
//)
//
//
//
//data class ReferenceInterval(
//    val min: Double? = null,
//    val max: Double? = null,
//    val isLessThan: Boolean = false,
//    val isGreaterThan: Boolean = false
//) {
//    fun getInterval(): String {
//        return when {
//            isLessThan && min != null -> "<$min"
//            isGreaterThan && max != null -> ">$max"
//            min != null && max != null -> "$min - $max"
//            else -> "-"
//        }
//    }
//}

@Preview(showSystemUi = true)
@Composable
private fun TestResultsPrev() {
    TestResultsScreen(
        state = HistoryState(
            searchText = "",
            testResults = emptyMap(),
            filteredTestResults = emptyMap()
        ),
        onUiEvent = {}
    )
}