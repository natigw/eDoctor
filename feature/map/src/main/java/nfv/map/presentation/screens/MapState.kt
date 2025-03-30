package nfv.map.presentation.screens

import com.google.android.gms.maps.model.LatLng

data class MapState(
    val currentLocation: LatLng,
    val pharmacyLocations: List<LatLng>
)