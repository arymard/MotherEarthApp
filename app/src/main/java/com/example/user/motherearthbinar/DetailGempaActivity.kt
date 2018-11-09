package com.example.user.motherearthbinar

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.TextView
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.annotations.MarkerOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory

private var mapView: MapView? = null

class DetailGempaActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mapbox Access token
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));

        // This contains the MapView in XML and needs to be called after the access token is configured.
        setContentView(R.layout.activity_detail_gempa_1)

        var dataModel = intent.getParcelableExtra<GempaModel>("data")
        var status = intent.getStringExtra("status")

        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync {
            // Customize map with markers, polylines, etc.private var mapView: MapView? = null
            it.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(
                        dataModel.latitude!!,
                        dataModel.longitude!!
                    ), 24000000000000000000000000000000000.0
                )
            )
            it.addMarker(MarkerOptions().position(LatLng(dataModel.latitude!!, dataModel.longitude!!)))
        }



        var tv_lokasi = findViewById<TextView>(R.id.tv_lokasi)
        var tv_status = findViewById<TextView>(R.id.tv_status)
        var tv_waktu = findViewById<TextView>(R.id.tv_waktu)
        var tv_kedalaman = findViewById<TextView>(R.id.tv_kedalaman)
        var tv_sr = findViewById<TextView>(R.id.detail_sr)
        tv_lokasi.text = "Lokasi    : ${dataModel.lokasi}"
        tv_status.text = "Status    : ${status}"
        tv_waktu.text = "Waktu      : ${dataModel.waktu}"
        tv_kedalaman.text = "Kedalaman      : ${dataModel.kedalaman}"
        tv_sr.text = dataModel.magnitude.toString()

        if(status == "aman"){
            tv_sr.setBackgroundResource(R.drawable.ellipse_sr)
        }else if(status == "bahaya"){
            tv_sr.setBackgroundResource(R.drawable.ellipse_sr_1)
        }else if(status == "waspada"){
            tv_sr.setBackgroundResource(R.drawable.ellipse_sr_2)
        }


    }
}
