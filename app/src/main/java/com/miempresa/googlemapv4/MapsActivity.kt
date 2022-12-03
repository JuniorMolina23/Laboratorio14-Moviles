package com.miempresa.googlemapv4

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.miempresa.googlemapv4.databinding.ActivityMapsBinding
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    AdapterView.OnItemSelectedListener {
    private var destino = ""
    var marcadorDestino: Marker? = null
    var coordenada = LatLng(0.0, 0.0)
    var mensaje = ""
    var imglugar = ""

    private val Plaza = LatLng(-16.3988031,-71.5374435)
    private val Chasracato = LatLng(-16.4704097,-71.4985087)

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private val tipos_mapas = intArrayOf(
        GoogleMap.MAP_TYPE_NORMAL,
        GoogleMap.MAP_TYPE_SATELLITE,
        GoogleMap.MAP_TYPE_HYBRID,
        GoogleMap.MAP_TYPE_TERRAIN
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        spnTipoMapa.setOnItemSelectedListener(this);

        val recibidos = this.intent.extras
        if (recibidos != null) {
            destino = intent.extras!!.getString("destino")!!
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
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setAllGesturesEnabled(true)

        mMap.getUiSettings().setCompassEnabled(true)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123);
        }

        when (destino) {
            "plaza de armas" -> {
                coordenada = LatLng(-16.3988031, -71.5374435)
                mensaje = "La plaza Mayor o plaza de Armas de Arequipa, " +
                        "es uno de los principales espacios públicos de Arequipa " +
                        "y el lugar de fundación de la ciudad"
                imglugar = "https://upload.wikimedia.org/wikipedia/commons/5/5f/Catedral_Arequipa%2C_Peru.jpg"
                marcadorDestino = mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "characato" ->{
                coordenada = LatLng(-16.4704097,-71.4985087)
                mensaje = "Localidad con mucha vegetación y abundante agua, ideal " +
                        "para la agricultura. Muy cerca al pueblo se ubica un ojo de" +
                        " agua llamado Ojo del Milagro. Posee una hermosa iglesia colonial."
                imglugar = "http://2.bp.blogspot.com/-WEZhUtmLHeo/VXxfHgQRPGI/AAAAAAAAQKc/XFt_Aviwy74/s1600/CHARACATO.jpg"
                btnMoverCamara.setText("Ir A characato")
                marcadorDestino =mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "colca" ->{
                coordenada = LatLng(-15.6093293,-72.0918116)
                mensaje = "El Cañón del Colca se encuentra en el valle de un río del sur de Perú y " +
                        "es famoso por ser uno de los más profundos del mundo. Es un destino famoso " +
                        "para el senderismo. Es un hábitat del cóndor Andino gigante, que se observa " +
                        "desde miradores como la Cruz del Cóndor."
                imglugar = "https://turismo.org.pe/wp-content/uploads/2019/01/ca%C3%B1on-colca.png"
                btnMoverCamara.setText("Ir al Cañon del colca")
                marcadorDestino =mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "yura" ->{
                coordenada = LatLng(-16.2526274,-71.7019542)
                mensaje ="El nombre de Yura proviene del vocablo quechua yurac, " +
                        "'blanco', que se habría usado para describir las llanuras salitrosas " +
                        "de color blanquecino ubicadas al Noroeste de la ciudad de Arequipa, " +
                        "donde se halla el distrito"
                imglugar = "https://www.turismo-peru.com/wp-content/uploads/2021/11/balneario-de-quiscos-en-yura.webp"
                btnMoverCamara.setText("Ir a Yura")
                marcadorDestino =mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            "mirador sachaca" ->{
                coordenada = LatLng(-16.4262814,-71.5716688)
                mensaje = "Un lugar ideal para pasar una tarde en Arequipa es el Mirador de " +
                        "Sachaca, en medio de una zona campestre y con una vista espectacular " +
                        "de la Ciudad Blanca y de los tres volcanes Misti, Chachani y Pichu si " +
                        "con suerte es un día soleado."
                imglugar = "https://upload.wikimedia.org/wikipedia/commons/8/8a/Mirador_de_Sachaca.jpg"
                btnMoverCamara.setText("Ir al Mirador de Sachaca")
                marcadorDestino =mMap.addMarker(
                    MarkerOptions()
                        .position(coordenada)
                        .title("Conoce: $destino")
                )
            }
            else -> {
                Toast.makeText(this, "No se encontro destino turistico", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        // Cámara
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 15f))
        // Eventos
        mMap.setOnMarkerClickListener(this)
    }
    fun moverCamara(view: View?){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 18f));
    }

    fun agregarMarcador(view: View?){
        mMap.addMarker(
            MarkerOptions().position(
                LatLng(
                    mMap.cameraPosition.target.latitude,
                    mMap.cameraPosition.target.longitude
                )
            )
                .title("Mi ubicacion")
        )
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        if (p0!!.equals(marcadorDestino)) {
            val intent = Intent(this, destinos::class.java)
            intent.putExtra("destino", destino)
            intent.putExtra("latitud", p0.getPosition().latitude.toString() + "")
            intent.putExtra("longitud", p0.getPosition().longitude.toString() + "")
            intent.putExtra("info", mensaje)
            intent.putExtra("img",imglugar)
            startActivity(intent)
            return true
        }
        return true
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        mMap.setMapType(tipos_mapas[p2]);
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}