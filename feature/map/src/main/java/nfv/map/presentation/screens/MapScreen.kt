package nfv.map.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.theme.BaseWhite
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@Composable
fun MapScreen(
    state: MapState,
    onUiEvent: (MapEvent) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .systemBarsPadding(),
        topBar = {
            TopBar(
                headerText = stringResource(stringR.header_maps),
                leadingIcon = IconWithAction(
                    icon = drawableR.ic_arrow_left,
                    action = {
                        onUiEvent(MapEvent.OnNavigateBack)
                    }
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {

            var isMapLoaded by remember {
                mutableStateOf(false)
            }

            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(state.currentLocation, 12f)
            }
            val uiSettings by remember {
                mutableStateOf(
                    MapUiSettings(
                        zoomControlsEnabled = false,
                    )
                )
            }

            val selectedMarker = rememberSaveable { mutableStateOf<LatLng?>(null) }

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings,
                onMapLoaded = {
                    isMapLoaded = true
                },
                onMapClick = {
                    selectedMarker.value = null // Close info window when clicking outside
                }
            ) {
                state.pharmacyLocations.forEach { location ->

                    CustomMapMarker(
                        fullName = location.toString(),
                        location = location,
                        onClick = {

                        }
                    )

//                var isMarkerExpanded by rememberSaveable { mutableStateOf(false) }
//
//                val location = rememberMarkerState(position = state.currentLocation)
//
//                MarkerComposable(
//                    state = location,
//                    onClick = {
//                        isMarkerExpanded = !isMarkerExpanded
//                        true
//                    }
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        if (isMarkerExpanded) {
//                            Card(
//                                modifier = Modifier.padding(bottom = 4.dp),
//                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
//                            ) {
//                                Text(
//                                    modifier = Modifier.padding(8.dp),
//                                    text = "Pharmacy",
//                                    color = BaseWhite
//                                )
//                            }
//                        }
//                        Icon(
//                            modifier = Modifier.size(40.dp),
//                            imageVector = ImageVector.vectorResource(drawableR.ic_map_pin_filled),
//                            contentDescription = "Pharmacy location",
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
                }
            }


            val lottieComposition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(
                    nfv.ui_kit.R.raw.map_loading_animation
                )
            )

            val progress by animateLottieCompositionAsState(
                lottieComposition,
                iterations = LottieConstants.IterateForever,
                isPlaying = true
            )

            if (isMapLoaded.not()) {
                LottieAnimation(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.75f)),
                    composition = lottieComposition,
                    progress = {
                        progress
                    }
                )
            }
        }
    }
}


@Composable
fun CustomMapMarker(
    fullName: String,
    location: LatLng,
    onClick: () -> Unit
) {
    val markerState = rememberMarkerState(position = location)
    val shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 0.dp)
    var expandMarker by remember {
        mutableStateOf(false)
    }

    MarkerComposable(
        keys = arrayOf(fullName, expandMarker),
        state = markerState,
        title = fullName,
        anchor = Offset(0.5f, 1f),
        onClick = {
            onClick()
            expandMarker = !expandMarker
            true
        }
    ) {
        Box(
            modifier = Modifier
                .size(if (expandMarker) 100.dp else 48.dp)
                .clip(shape)
                .background(BaseWhite)
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(expandMarker) {
                Card(
                    modifier = Modifier.padding(bottom = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Pharmacy",
                        color = BaseWhite
                    )
                }
            }
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = ImageVector.vectorResource(drawableR.ic_map_pin_filled),
                contentDescription = "Pharmacy location",
                tint = MaterialTheme.colorScheme.primary
            )

        }
    }
}

@Preview
@Composable
private fun MapScreenPrev() {
    MapScreen(
        state = MapState(
            currentLocation = LatLng(40.3791, 49.8468),
            pharmacyLocations = emptyList()
        ),
        onUiEvent = {}
    )
}