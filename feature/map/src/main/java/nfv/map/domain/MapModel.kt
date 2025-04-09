package nfv.map.domain

import com.google.android.gms.maps.model.LatLng

data class MapModel(
    val location: LatLng,
    val name: String,
    val description: String?
)