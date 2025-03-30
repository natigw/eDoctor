package nfv.map.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nfv.navigation.di.Navigator
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    val uiState = MutableStateFlow(
        MapState(
            currentLocation = LatLng(40.3791, 49.8468), //baku
            pharmacyLocations = listOf(
                LatLng(40.3847279, 49.8056599),
                LatLng(40.3791, 49.8468)
            )
        )
    )

    fun handleEvent(event: MapEvent) {

        when(event) {

            MapEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    navigator.sendCommand {
                        popBackStack()
                    }
                }
            }

        }
    }

}