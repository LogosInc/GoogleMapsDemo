package com.example.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.lifecycleScope

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemapsdemo.databinding.ActivityMapsBinding
import com.example.googlemapsdemo.misc.CameraAndViewport
import com.example.googlemapsdemo.misc.TypeAndStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val typeAndStyle by lazy { TypeAndStyle() }
    private val cameraAndViewport by lazy { CameraAndViewport() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        typeAndStyle.setMapType(item, map)
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val losAngeles = LatLng(34.1059699196864, -118.20171661595992)
        val newYork = LatLng(40.71614203933524, -74.00440676650565)
        map.addMarker(MarkerOptions().position(losAngeles).title("Marker in Luodong"))
        // Zoom level from 1~20f
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(losAngeles, 10f), 2000, null)

        map.uiSettings.apply {
            // Zoom in/out default is disable
            isZoomControlsEnabled = true
        }
        typeAndStyle.setMapStyle(map, this)

        onMapClicked()
        onMapLongClicked()
/*
        lifecycleScope.launch {
            delay(4000L)
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewport.losAngeles), 2000,
            object : GoogleMap.CancelableCallback {
                override fun onFinish() {
                    Toast.makeText(this@MapsActivity, "Finished", Toast.LENGTH_SHORT).show()
                }

                override fun onCancel() {
                    Toast.makeText(this@MapsActivity, "Cancelled", Toast.LENGTH_SHORT).show()
                }
            })
            //map.animateCamera(CameraUpdateFactory.scrollBy(200f, 0f), 2000, null)
            // map.animateCamera(CameraUpdateFactory.newLatLngBounds(cameraAndViewport.melbourneBounds, 100), 2000, null)
            // map.setLatLngBoundsForCameraTarget(cameraAndViewport.melbourneBounds)
        }

        lifecycleScope.launch {
            delay(4000L)
            map.moveCamera(CameraUpdateFactory.zoomBy(3f))
        }

          map.setMinZoomPreference(15f)
          map.setMaxZoomPreference(17f)

          // use for add side bar
          // (left, top, right, bottom)
          map.setPadding(0, 0, 300, 0)
         */
    }

    private fun onMapClicked() {
        map.setOnMapClickListener {
            Toast.makeText(this, "Single click", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onMapLongClicked() {
        map.setOnMapLongClickListener {
            map.addMarker(MarkerOptions().position(it).title(" New Marker"))
        // Toast.makeText(this, "${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
        }
    }
}