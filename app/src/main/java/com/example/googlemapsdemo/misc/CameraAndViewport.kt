package com.example.googlemapsdemo.misc

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

class CameraAndViewport {

    val losAngeles: CameraPosition = CameraPosition.Builder()
        .target(LatLng(34.1059699196864, -118.20171661595992))
        .zoom(17f)
        .bearing(100f)
        .tilt(45f)
        .build()

    val melbourneBounds = LatLngBounds(
        LatLng(-38.45007744433469, 144.2546884913213), // SW
        LatLng(-37.522250857924213, 145.56755471879976) // NE
    )

}