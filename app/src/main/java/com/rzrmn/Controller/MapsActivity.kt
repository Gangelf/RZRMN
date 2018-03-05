package com.rzrmn.Controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rzrmn.Model.Location
import com.rzrmn.R
import com.rzrmn.Services.LocationDataService
import com.rzrmn.Services.LocationService

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    lateinit var locationList: MutableList<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        locationList = mutableListOf()
        getLocations{locationsSuccess ->
            if (locationsSuccess){
                // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                val mapFragment = supportFragmentManager
                        .findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync(this)
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val minnesota = LatLng(46.392410, -94.636230)

        for(l in locationList){
            val location = LatLng(l.Lat,l.Long)
            
        }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(minnesota))
            mMap.setOnInfoWindowClickListener {marker ->
                marker.title
                Toast.makeText(this, "Clicked ${marker.title}", Toast.LENGTH_LONG).show()
            }
    }

     private fun getLocations(complete: (Boolean) -> Unit){
         val fbDatabase = FirebaseDatabase.getInstance()
         val ref = fbDatabase.reference.child("MN Locations")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if (p0!!.exists()){
                    for (l in p0.children){
                        val location = l.getValue(Location::class.java)
                        locationList.add(location!!)
                        complete(true)
                    }
                }
            }

        })



    }
}
