package nfv.map.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nfv.map.domain.MapRepository
import nfv.navigation.di.Navigator
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val navigator: Navigator,
    private val repository: MapRepository
) : ViewModel() {

    val uiState = MutableStateFlow(
        MapState(
            currentLocation = LatLng(40.3791, 49.8468),  //Baku
            pharmacies = emptyList()
        )
    )

    init {
        viewModelScope.launch {
            val response = repository.getPharmacies()
            if (response != null) {
                uiState.update { old ->
                    old.copy(
                        pharmacies = response
                    )
                }
            }
        }
    }

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