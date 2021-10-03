package com.icg.training

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.icg.training.databinding.ActivityMapsBinding
import java.lang.StringBuilder
import java.util.*
import kotlin.math.ln


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    var currentMarker: Marker?=null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    var currentLocation: Location?=null
    var lat=0.0
    var lng=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        fetchLocation()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    private fun fetchLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                    showRationaleDialog(
                        getString(R.string.title_rationale),
                        getString(R.string.desc_rationale),
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Constants.Codes.REQ_CODE
                    )
                }else{
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), Constants.Codes.REQ_CODE)
                }
            }

        }else{
            val task = fusedLocationClient.lastLocation
            task.addOnSuccessListener {location ->
                if (location != null){
                    this.currentLocation = location
                    val mapFragment = supportFragmentManager
                        .findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)
                }else{
                    val mapFragment = supportFragmentManager
                        .findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)
                }
            }
        }


    }

    private fun showRationaleDialog(title: String, message: String, permission: String, reqCode: Int) {
        with(AlertDialog.Builder(this)){
            setCancelable(false)
            setTitle(title)
            setMessage(message)
            setPositiveButton("OKAY"){dialog,_->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    requestPermissions(arrayOf(permission), reqCode)
                    dialog.dismiss()
                }

            }.show()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            Constants.Codes.REQ_CODE->if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                fetchLocation()

            }else {
                AlertDialog.Builder(this)
                    .setMessage("This permission is disabled. Enable it from settings")
                    .setPositiveButton("OKAY"){dialog,_->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        val uri: Uri = Uri.fromParts("package", packageName, null)
                        intent.data = uri
                        startActivity(intent)

                    }.setNegativeButton("CANCEL"){dialog,_ ->
                        dialog.dismiss()
                    }
                    .show()

            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        val bundle = intent.extras
        when {
            bundle != null -> {

                lat = bundle.getDouble(Constants.Keys.lat)
                lng = bundle.getDouble(Constants.Keys.lng)
                if (lat != 0.0 && lng != 0.0){
                    val locate = LatLng(lat, lng)
                    drawMarker(locate)

                }

            }
            currentLocation != null -> {

                val latLong = LatLng(currentLocation?.latitude!!,currentLocation?.longitude!!)
                drawMarker(latLong)
            }
            else -> {
                val sydneyLatLng = LatLng(-33.8688, 151.2093)
                drawMarker(sydneyLatLng)
            }
        }
    }
    private fun drawMarker(latLng: LatLng){
        val markerOption = MarkerOptions().position(latLng).title("I am here").snippet(getAddress(latLng.latitude,latLng.longitude))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
        currentMarker = mMap.addMarker(markerOption)
        currentMarker?.showInfoWindow()
        currentMarker?.isDraggable = true








    }

    private fun getAddress(latitude: Double, longitude: Double):String? {
        return try {

            val geocoder = Geocoder(this, Locale.getDefault())
            val address =  geocoder.getFromLocation(latitude,longitude, 1)

            address[0].getAddressLine(0).toString()

        }catch (e:Exception){
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            "Error"
        }

    }


}