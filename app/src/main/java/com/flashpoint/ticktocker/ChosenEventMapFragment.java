package com.flashpoint.ticktocker;

import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;

import java.io.IOException;
import java.util.HashMap;

public class ChosenEventMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    private int curMapTypeIndex = 0;
    private final int[] MAP_TYPES = {
            GoogleMap.MAP_TYPE_SATELLITE,
            GoogleMap.MAP_TYPE_NORMAL,
            GoogleMap.MAP_TYPE_HYBRID,
            GoogleMap.MAP_TYPE_TERRAIN,
            GoogleMap.MAP_TYPE_NONE};

    private EventInfo eventInfo;

    private Marker currentMarker;


    public void setEventInfo(EventInfo a){
        this.eventInfo = a;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getMapAsync(this);

        setHasOptionsMenu(true);

        mGoogleApiClient = new GoogleApiClient.Builder(this.getActivity())
                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map_event_menu, menu);

        getActivity().setTitle("Map");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(getContext(), "hey", Toast.LENGTH_SHORT).show();
        FragmentActivity activity = getActivity();
        MainActivity mainActivity = (MainActivity) activity;
        mainActivity.goBack();
        //mainActivity.showFragment(new CreateEventFragment());
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
//        mCurrentLocation = LocationServices
//                .FusedLocationApi
//                .getLastLocation(mGoogleApiClient);
//
//        initCamera(mCurrentLocation);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
//
//    private void initCamera( Location location ) {
//        CameraPosition position = CameraPosition.builder()
//                .target( new LatLng( location.getLatitude(),
//                        location.getLongitude() ) )
//                .zoom( 16f )
//                .bearing( 0.0f )
//                .tilt( 0.0f )
//                .build();
//
//        getMap().animateCamera( CameraUpdateFactory
//                .newCameraPosition( position ), null );
//
//        getMap().setMapType( MAP_TYPES[curMapTypeIndex] );
//        getMap().setTrafficEnabled( true );
//        getMap().setMyLocationEnabled( true );
//        getMap().getUiSettings().setZoomControlsEnabled( true );
//    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        //googleMap.addMarker(new MarkerOptions().position(eventInfo.location).title(eventInfo.getEvent()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventInfo.location, 13));
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(eventInfo.location)
                .title(eventInfo.getEvent())
                .snippet(eventInfo.getHour().toString()+":"+eventInfo.getMinute().toString()));
        marker.showInfoWindow();
    }



}
