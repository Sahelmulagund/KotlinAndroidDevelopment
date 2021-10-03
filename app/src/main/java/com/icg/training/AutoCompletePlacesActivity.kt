package com.icg.training


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*


class AutoCompletePlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_places)
        if (!Places.isInitialized()){
            Places.initialize(applicationContext, getString(R.string.api_key))
        }

        val placesClient = Places.createClient(this)

        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment?

        autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))
        autocompleteFragment?.setOnPlaceSelectedListener(object:PlaceSelectionListener{
            override fun onPlaceSelected(p0: Place) {
                if (p0.latLng != null){
                    val bundle = Bundle()
                    val intent = Intent(this@AutoCompletePlacesActivity, MapsActivity::class.java)
                    p0.latLng?.latitude?.let { bundle.putDouble(Constants.Keys.lat, it) }
                    p0.latLng?.longitude?.let { bundle.putDouble(Constants.Keys.lng, it) }
                    intent.putExtras(bundle)
                    startActivity(intent)
                }




            }

            override fun onError(p0: Status) {
                Toast.makeText(applicationContext, "Error ${p0.statusMessage}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}