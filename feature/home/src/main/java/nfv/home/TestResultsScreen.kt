package nfv.home

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ripple
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import nfv.ui_kit.components.IconWithAction
import nfv.ui_kit.components.SearchBar
import nfv.ui_kit.components.TopAppBar
import nfv.ui_kit.theme.BaseTransparent
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.theme.EDoctorTypography
import nfv.ui_kit.theme.Info50
import nfv.ui_kit.theme.Primary900
import nfv.ui_kit.theme.Typography500
import nfv.ui_kit.theme.Typography900
import java.util.Date
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun TestResultsScreen(
    modifier: Modifier = Modifier,
    onClickBack: ()-> Unit
) {
    Scaffold(
        modifier = modifier.systemBarsPadding(),
        topBar = {
            var isSearchClicked by remember { mutableStateOf(false) }
            Column {
                TopAppBar(
                    headerText = stringResource(stringR.header_test_results),
                    leadingIcon = IconWithAction(
                        icon = drawableR.ic_arrow_left,
                        action = onClickBack
                    ),
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
                    SearchBar(searchKeywords = stringResource(stringR.search_for_result))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(BaseWhite)
                .padding(innerPadding)
        ) {
            val data = mapOf(
                "Yanvar" to listOf(
                    TestResultItem(
                        0,
                        "Qan analizi",
                        "Referans Lab",
                        "10 yanvar"
                    ),
                    TestResultItem(
                        1,
                        "Pregnancy test",
                        "Merkezi klinika",
                        "12 yanvar"
                    ),
                    TestResultItem(
                        2,
                        "Qlükoza",
                        "Merkezi klinika",
                        "15 yanvar"
                    )
                ),
                "Mart" to listOf(
                    TestResultItem(
                        3,
                        "Xolesterin",
                        "Referans Lab",
                        "5 fevral"
                    )
                ),
                "Iyun" to listOf(
                    TestResultItem(
                        4,
                        "D vitamini",
                        "Referans Lab",
                        "20 fevral"
                    ),
                    TestResultItem(
                        5,
                        "Kalsium",
                        "Merkezi klinika",
                        "28 fevral"
                    )
                ),
                "Yanvar1" to listOf(
                    TestResultItem(
                        0,
                        "Qan analizi",
                        "Referans Lab",
                        "10 yanvar"
                    ),
                    TestResultItem(
                        1,
                        "Pregnancy test",
                        "Merkezi klinika",
                        "12 yanvar"
                    ),
                    TestResultItem(
                        2,
                        "Qlükoza",
                        "Merkezi klinika",
                        "15 yanvar"
                    )
                ),
                "Mart2" to listOf(
                    TestResultItem(
                        3,
                        "Xolesterin",
                        "Referans Lab",
                        "5 fevral"
                    )
                ),
                "Iyun3" to listOf(
                    TestResultItem(
                        4,
                        "D vitamini",
                        "Referans Lab",
                        "20 fevral"
                    ),
                    TestResultItem(
                        5,
                        "Kalsium",
                        "Merkezi klinika",
                        "28 fevral"
                    )
                )
            )
            ResultListByMonth(groupedResults = data)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultListByMonth(
    modifier: Modifier = Modifier,
    groupedResults: Map<String, List<TestResultItem>> //TODO stringi date etmek lazimdi??
) {
    LazyColumn(
        modifier = modifier
    ) {
        groupedResults.forEach { (date, resultList) ->

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
                    animationSpec = tween(durationMillis = 300),
                    label = "RotationAnimation"
                )

                var isRead by remember { mutableStateOf(false) }

                Column {
                    Row(
                        modifier = Modifier
                            .background(if (isRead) BaseTransparent else Info50)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(),
                                onClick = {
                                    isExpanded = !isExpanded
                                    isRead = true
                                }
                            )
                            .padding(vertical = 12.dp, horizontal = 16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = resultList[testResult].title,
                                style = EDoctorTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(4.dp))
                            if (isExpanded) {
                                Text(
                                    text = "description",
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
                                    text = stringResource(stringR.test_date) + resultList[testResult].resultDate,
                                    style = EDoctorTypography.labelMedium.copy(color = Typography500),
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height(4.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "download pdf", //TODO -> bunu button component ile evez et
                                        style = EDoctorTypography.labelMedium.copy(color = Typography500),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Icon(
                                        imageVector = ImageVector.vectorResource(drawableR.ic_arrow_top_right_16),
                                        contentDescription = stringResource(stringR.download_pdf),
                                        tint = Typography500
                                    )
                                }
                            } else {
                                Text(
                                    text = "${resultList[testResult].resultDate} • ${resultList[testResult].labName}",
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

data class TestResultItem(
    val resultId: Int,
    val title: String,
    val labName: String,
    val resultDate: String //Date TODO bunu date etmek lazimdi?
)

data class TestResultDescriptionItem(
    val resultId: Int,
    val patientId: Int,
    val title: String,
    val description: String,
    val result: Double, //TODO-> bunu string etmek yaxsidi? cunki bezi neticeler 50mq/l, bezileri ela pis ola biler
    val previousResult: Double?,
    val measure: String, //TODO -> bu enum olmalidi??
    val referenceInterval: ReferenceInterval,
    val labName: String,
    val testDate: String,  //Date TODO bunu date etmek lazimdi?
    val resultDate: String //Date TODO bunu date etmek lazimdi?
)

data class PatientDetails(
    val id: Int,
    val fullName: String,
    val sex: Sex,
    val birthDate: Date
)

enum class Sex {
    MALE,
    FEMALE
}

data class ReferenceInterval(
    val min: Double? = null,
    val max: Double? = null,
    val isLessThan: Boolean = false,
    val isGreaterThan: Boolean = false
) {
    fun getInterval(): String {
        return when {
            isLessThan && min != null -> "<$min"
            isGreaterThan && max != null -> ">$max"
            min != null && max != null -> "$min - $max"
            else -> "-"
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TestResultsPrev() {
    TestResultsScreen(onClickBack = { })
}