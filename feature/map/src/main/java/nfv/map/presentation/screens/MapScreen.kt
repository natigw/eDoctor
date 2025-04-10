package nfv.map.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.rememberCameraPositionState
import nfv.map.domain.MapModel
import nfv.ui_kit.components.systemBars.IconWithAction
import nfv.ui_kit.components.systemBars.TopBar
import nfv.ui_kit.R.drawable as drawableR
import nfv.ui_kit.R.string as stringR

@OptIn(MapsComposeExperimentalApi::class)
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

            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings,
                onMapLoaded = {
                    isMapLoaded = true
                }
            ) {

                Clustering(
                    items = state.pharmacies.map {
                        MarkerData(item = it)
                    },
                    onClusterClick = {
                        cameraPositionState.move(
                            update = CameraUpdateFactory.zoomIn()
                        )
                        false
                    },
                    onClusterItemClick = {
                        cameraPositionState.move(
                            update = CameraUpdateFactory.zoomBy(1.8f)
                        )
                        false
                    },
//                    clusterItemContent = @Composable { markerData ->
//                        MarkerComposable(
//                            state = rememberMarkerState(position = markerData.position),
//                            title = markerData.title,
//                            snippet = markerData.snippet,
//                            onClick = {
//                                cameraPositionState.move(
//                                    update = CameraUpdateFactory.zoomBy(1.8f)
//                                )
//                                false
//                            }
//                        ) {
//                            Icon(
//                                modifier = Modifier.size(40.dp),
//                                imageVector = ImageVector.vectorResource(drawableR.ic_map_pin_filled),
//                                contentDescription = "Pharmacy location",
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                    }
                )

//                state.pharmacies.forEach { location ->
//                    MarkerComposable(
//                        state = rememberMarkerState(position = location.location),
//                        title = location.name,
//                        snippet = location.description,
//                        onClick = {
//                            cameraPositionState.move(
//                                update = CameraUpdateFactory.zoomBy(1.8f)
//                            )
//                            false
//                        }
//                    ) {
//                        Icon(
//                            modifier = Modifier.size(40.dp),
//                            imageVector = ImageVector.vectorResource(drawableR.ic_map_pin_filled),
//                            contentDescription = "Pharmacy location",
//                            tint = MaterialTheme.colorScheme.primary
//                        )
//                    }
//                }
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

data class MarkerData(
    val item: MapModel
) : ClusterItem {

    override fun getPosition(): LatLng = item.location

    override fun getTitle(): String = item.name

    override fun getSnippet(): String? = item.description

    override fun getZIndex(): Float = 1f

}

@Preview
@Composable
private fun MapScreenPrev() {
    MapScreen(
        state = MapState(
            currentLocation = LatLng(40.3791, 49.8468),
            pharmacies = emptyList()
        ),
        onUiEvent = {}
    )
}