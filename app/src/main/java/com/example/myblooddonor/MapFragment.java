package com.example.myblooddonor;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements LocationListener {


    GeoPoint p;
    GeoPoint loc;
    MapView mMapView;
    MapController mMapController;
    LocationManager locationManager;
    LocationListener locationListener;
    Context mContext;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_map, container, false);

        if (Build.VERSION.SDK_INT >= 23) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission is granted",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Permission is  not granted",Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }

        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getContext()));

        mMapView = (MapView) root.findViewById(R.id.mapview2);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        mMapView.setBuiltInZoomControls(true);
        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(10);
        //Position
//        GeoPoint gPt = new GeoPoint(36.7301504, 10.335570599999983);
//        mMapController.setCenter(gPt);
        mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        position= new Marker(mMapView);


        double lat = (double) Float.valueOf(getArguments().getString("lat"));
        double lng = (double) Float.valueOf(getArguments().getString("long"));

        System.out.println("map frag  :"+ getArguments().getString("lat") +", "+ getArguments().getString("long"));

        Location l = new Location("");
        l.setLatitude(lat);
        l.setLongitude(lng);
        onLocationChanged(l);
        Marker startMarker = new Marker(mMapView);
        GeoPoint rest = new GeoPoint(lat,lng);
        startMarker.setPosition(rest);


        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(startMarker);
        startMarker.setIcon(getResources().getDrawable(R.drawable.marker_default));
        //startMarker.setTitle(getArguments().getString("titre"));


        locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return root;
        }
        // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);





        return root;

    }


    private MyLocationNewOverlay mLocationOverlay;

    Marker position ;

    @Override
    public void onLocationChanged(Location location) {
        GeoPoint gPt = new GeoPoint(location.getLatitude(), location.getLongitude());
        mMapController.setCenter(gPt);

        position.setPosition(gPt);
        position.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        mMapView.getOverlays().add(position);

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
