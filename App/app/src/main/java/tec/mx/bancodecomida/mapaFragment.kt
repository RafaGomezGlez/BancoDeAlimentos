package tec.mx.bancodecomida

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class mapa : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val BAMX = LatLng(20.656286973014126, -103.35543680445372)
        val coloniaRehilete = LatLng(20.645025289320962, -103.46241001305546)
        val talpita = LatLng(20.684264410787097, -103.30492067957613)
        val sergioBarrios = LatLng(20.440232061097518, -103.36590460989119)

        with(googleMap) {
            addMarker(
                MarkerOptions()
                    .position(BAMX)
                    .title("Banco de Alimentos Guadalajara")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.communityicon_foreground)).anchor(0.5f,0.5f)
            ).showInfoWindow()
            addMarker(
                MarkerOptions()
                    .position(coloniaRehilete)
                    .title("Colonia El Rehilete")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.alimentada_foreground)).anchor(0.5f,0.5f)
            ).showInfoWindow()
            addMarker(
                MarkerOptions()
                    .position(talpita)
                    .title("Colonia Talpita")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.alimentada_foreground)).anchor(0.5f,0.5f)
            ).showInfoWindow()
            addMarker(
                MarkerOptions()
                    .position(sergioBarrios)
                    .title("Colonia Indigena Sergio Barrios")
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.alimentada_foreground)).anchor(0.5f,0.5f)
            ).showInfoWindow()
            // Move the camera to the map coordinates and zoom in closer.
            // Move the camera to the map coordinates and zoom in closer.
            moveCamera(CameraUpdateFactory.newLatLng(BAMX))
            moveCamera(CameraUpdateFactory.zoomTo(11.5f))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}