package com.example.myblooddonor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

public class AddLocationActivity extends AppCompatActivity  implements LocationListener{
    GeoPoint p;
    GeoPoint loc;
    MapView mMapView;
    MapController mMapController;
    LocationManager locationManager;
    LocationListener locationListener;
    Context mContext;
    Marker position ;
    public static String lng="";
    public static  String  lat = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);


        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission is granted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission is  not granted",Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        Configuration.getInstance().load(AddLocationActivity.this, PreferenceManager.getDefaultSharedPreferences(AddLocationActivity.this));



        mMapView = (MapView) findViewById(R.id.mapview1);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        mMapView.setBuiltInZoomControls(true);
        mMapController = (MapController) mMapView.getController();
        // mMapController.setZoom(15);
        //Position
//        GeoPoint gPt = new GeoPoint(36.7301504, 10.335570599999983);
//        mMapController.setCenter(gPt);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        position= new Marker(mMapView);



        // setContentView(mMapView);


        MapEventsReceiver mer=new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {

                return false;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {

                //  an.locationToString(p);
                Intent i=new Intent();
                lat = String.valueOf(p.getLatitude());
                lng = String.valueOf(p.getLongitude());
                setResult(Activity.RESULT_OK,i);
                finish();
                return true;
            }
        };

        MapEventsOverlay OverlayEvents = new MapEventsOverlay(this, mer);
        mMapView.getOverlays().add(OverlayEvents);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);







    }

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint gPt = new GeoPoint(location.getLatitude(), location.getLongitude());
        mMapController.setCenter(gPt);

        position.setPosition(gPt);
        position.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(position);
        position.setIcon(getResources().getDrawable(R.drawable.ic_menu_mylocation));
        Toast.makeText(this,String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude()),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
