package nfv.map.presentation.screens

import com.google.android.gms.maps.model.LatLng
import nfv.map.domain.MapModel

data class MapState(
    val currentLocation: LatLng,
    val pharmacies: List<MapModel>
)