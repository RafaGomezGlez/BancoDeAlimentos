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
        //googleMap.addMarker(MarkerOptions().position(zapopan).title("Marker in Zapopan"))
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(zapopan))
        googleMap.addMarker(
            MarkerOptions()
                .position(BAMX)
                .title("Banco de Alimentos Guadalajara")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.communityicon_foreground)).anchor(0.5f,0.5f)
        ).showInfoWindow()
        googleMap.addMarker(
            MarkerOptions()
                .position(coloniaRehilete)
                .title("Colonia el Rehilete")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.alimentada_foreground)).anchor(0.5f,0.5f)
        ).showInfoWindow()
        // Move the camera to the map coordinates and zoom in closer.
        // Move the camera to the map coordinates and zoom in closer.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(BAMX))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(11.5f))

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