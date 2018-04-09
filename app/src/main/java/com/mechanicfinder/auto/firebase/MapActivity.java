package com.mechanicfinder.auto.firebase;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.mechanicfinder.auto.firebase.models.PlaceInfo;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener{

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        //Add markers here
        LatLng EdenVulcanizingShop = new LatLng(14.534158, 121.015825);
        googleMap.addMarker(new MarkerOptions()
                .position(EdenVulcanizingShop)
                .title("Eden Vulcanizing Shop")
                .alpha(0.7f));


        LatLng IlluminadaVulcanizingShop = new LatLng(14.527415, 121.014237);
        googleMap.addMarker(new MarkerOptions()
                .position(IlluminadaVulcanizingShop)
                .title("Illuminada Vulcanizing Shop")
                .alpha(0.7f));

        LatLng EJCJVulcanizingAndWeldingShop = new LatLng(14.536693, 121.004543);
        googleMap.addMarker(new MarkerOptions()
                .position(EJCJVulcanizingAndWeldingShop)
                .title("EJCJ Vulcanizing and Welding Shop")
                .alpha(0.7f));

        LatLng NonogImeldaVulcanizingShop = new LatLng(14.541289, 120.995709);
        googleMap.addMarker(new MarkerOptions()
                .position(NonogImeldaVulcanizingShop)
                .title("Nonog Imelda Vulcanizing Shop")
                .alpha(0.7f));

        LatLng JigaMagsAndTireSupply = new LatLng(14.538342, 121.011463);
        googleMap.addMarker(new MarkerOptions()
                .position(JigaMagsAndTireSupply)
                .title("Jiga Mags and Tire Supply")
                .alpha(0.7f));

        LatLng GoMagsTrading = new LatLng(14.540827, 121.01179);
        googleMap.addMarker(new MarkerOptions()
                .position(GoMagsTrading)
                .title("Gomags Trading")
                .alpha(0.7f));

        LatLng ZiebartCarsavers = new LatLng(14.548979, 121.013764);
        googleMap.addMarker(new MarkerOptions()
                .position(ZiebartCarsavers)
                .title("Ziebart/Carsavers Makati")
                .alpha(0.7f));

        LatLng ChristianVulcanizing = new LatLng(14.549635, 121.005275);
        googleMap.addMarker(new MarkerOptions()
                .position(ChristianVulcanizing)
                .title("Christian Vulcanizing")
                .alpha(0.7f));

        LatLng TiauraVulcanizing = new LatLng(14.551351, 121.005567);
        googleMap.addMarker(new MarkerOptions()
                .position(TiauraVulcanizing)
                .title("Tiaura Vulcanizing Shop")
                .alpha(0.7f));

        LatLng MLMarquezVulcanizing = new LatLng(14.555725, 120.993463);
        googleMap.addMarker(new MarkerOptions()
                .position(MLMarquezVulcanizing)
                .title("ML Marquez Trading and Vulcanizing Shop")
                .alpha(0.7f));

        LatLng RomnicsVulcanizing = new LatLng(14.556154, 121.010618);
        googleMap.addMarker(new MarkerOptions()
                .position(RomnicsVulcanizing)
                .title("Romnics Vulcanizing Shop")
                .alpha(0.7f));

        LatLng ThreeNVulcanizing = new LatLng(14.531568, 121.03337);
        googleMap.addMarker(new MarkerOptions()
                .position(ThreeNVulcanizing)
                .title("3 N's Radiator Repair & Vulcanizing Shop")
                .alpha(0.7f));

        LatLng IsabelitaVulcanizing = new LatLng(14.539277, 120.99545);
        googleMap.addMarker(new MarkerOptions()
                .position(IsabelitaVulcanizing)
                .title("Isabelita Vulcanizing Shop")
                .alpha(0.7f));

        LatLng McQueenAutoRepair = new LatLng(14.555091, 121.0005106);
        googleMap.addMarker(new MarkerOptions()
                .position(McQueenAutoRepair)
                .title("McQueen Auto Repair Shop")
                .alpha(0.7f));

        LatLng ConstellationAutoRepair = new LatLng(14.554143, 121.004961);
        googleMap.addMarker(new MarkerOptions()
                .position(ConstellationAutoRepair)
                .title("Constellation Auto Repair Shop")
                .alpha(0.7f));

        LatLng BigAutoTech = new LatLng(14.54963, 121.010619);
        googleMap.addMarker(new MarkerOptions()
                .position(BigAutoTech)
                .title("Big-A Auto Tech Enterprises")
                .alpha(0.7f));

        LatLng AguiaAutoGlass = new LatLng(14.54452, 121.010254);
        googleMap.addMarker(new MarkerOptions()
                .position(AguiaAutoGlass)
                .title("Aguia Auto Glass")
                .alpha(0.7f));

        LatLng JophilAutoShop = new LatLng(14.54182, 121.014682);
        googleMap.addMarker(new MarkerOptions()
                .position(JophilAutoShop)
                .title("Jophil Auto Shop")
                .alpha(0.7f));

        LatLng AutoHomeMotorWorks = new LatLng(14.544204, 121.012963);
        googleMap.addMarker(new MarkerOptions()
                .position(AutoHomeMotorWorks)
                .title("Auto Home Motor Works")
                .alpha(0.7f));

        LatLng SapscoAutoServices = new LatLng(14.542099, 121.008983);
        googleMap.addMarker(new MarkerOptions()
                .position(SapscoAutoServices)
                .title("Sapsco Auto Services Inc. ")
                .alpha(0.7f));

        LatLng JonasAutoShop = new LatLng(14.543138, 121.010772);
        googleMap.addMarker(new MarkerOptions()
                .position(JonasAutoShop)
                .title("Jonas Auto Shop Parts Trading")
                .alpha(0.7f));

        LatLng JamesMotorShop = new LatLng(14.543991, 121.013108);
        googleMap.addMarker(new MarkerOptions()
                .position(JamesMotorShop)
                .title("James Motor Shop")
                .alpha(0.7f));

        LatLng SummitAutoCareZone = new LatLng(14.546352, 121.014446);
        googleMap.addMarker(new MarkerOptions()
                .position(SummitAutoCareZone)
                .title("Summit Auto Care Zone")
                .alpha(0.7f));

        LatLng ElBicolanoMotor = new LatLng(14.547785, 121.009838);
        googleMap.addMarker(new MarkerOptions()
                .position(ElBicolanoMotor)
                .title("El Bicolano Motor")
                .alpha(0.7f));

        LatLng AsphireMotors = new LatLng(14.518066, 120.997938);
        googleMap.addMarker(new MarkerOptions()
                .position(AsphireMotors)
                .title("Asphire Motors")
                .alpha(0.7f));

        LatLng GreenPartsAutoSupply = new LatLng(14.523895, 120.99572);
        googleMap.addMarker(new MarkerOptions()
                .position(GreenPartsAutoSupply)
                .title("Green Parts Auto Supply")
                .alpha(0.7f));

        LatLng PaylessCarAndCareCenter = new LatLng(14.516479, 120.999425);
        googleMap.addMarker(new MarkerOptions()
                .position(PaylessCarAndCareCenter)
                .title("Payless Car And Care Center")
                .alpha(0.7f));

        LatLng SuzukiServiceCenter = new LatLng(14.538963, 120.987401);
        googleMap.addMarker(new MarkerOptions()
                .position(SuzukiServiceCenter)
                .title("Suzuki Service Center")
                .alpha(0.7f));

        LatLng ManArAutoRepairShop = new LatLng(14.54329, 120.990825);
        googleMap.addMarker(new MarkerOptions()
                .position(ManArAutoRepairShop)
                .title("Man-Ar AutoRepair Shop")
                .alpha(0.7f));

        LatLng LargeCalibrationServiceCenter = new LatLng(14.546399, 120.994357);
        googleMap.addMarker(new MarkerOptions()
                .position(LargeCalibrationServiceCenter)
                .title("Large Calibration Service Center")
                .alpha(0.7f));

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            init();
        }
    }

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));


    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps, mInfo, mPlacePicker;


    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private Marker mMarker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);
        mInfo = (ImageView) findViewById(R.id.place_info);
        mPlacePicker = (ImageView) findViewById(R.id.place_picker);

        getLocationPermission();

    }

    private void init(){
        Log.d(TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mSearchText.setOnItemClickListener(mAutocompleteClickListener);

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction() == KeyEvent.ACTION_DOWN
                        || keyEvent.getAction() == KeyEvent.KEYCODE_ENTER){

                    //execute our method for searching
                    geoLocate();
                }

                return false;
            }
        });

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked gps icon");
                getDeviceLocation();
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked place info");
                try{
                    if(mMarker.isInfoWindowShown()){
                        mMarker.hideInfoWindow();
                    }else{
                        Log.d(TAG, "onClick: place info: " + mPlace.toString());
                        mMarker.showInfoWindow();
                    }
                }catch (NullPointerException e){
                    Log.e(TAG, "onClick: NullPointerException: " + e.getMessage() );
                }
            }
        });

        mPlacePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(MapActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.e(TAG, "onClick: GooglePlayServicesRepairableException: " + e.getMessage() );
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.e(TAG, "onClick: GooglePlayServicesNotAvailableException: " + e.getMessage() );
                }
            }
        });

        hideSoftKeyboard();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);

                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, place.getId());
                placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            }
        }
    }

    private void geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location: " + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");

                        }else{
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        mMap.clear();

        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(MapActivity.this));

        if(placeInfo != null){
            try{
                String snippet = "Address: " + placeInfo.getAddress() + "\n" +
                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
                        "Price Rating: " + placeInfo.getRating() + "\n";

                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(placeInfo.getName())
                        .snippet(snippet);
                mMarker = mMap.addMarker(options);

            }catch (NullPointerException e){
                Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage() );
            }
        }else{
            mMap.addMarker(new MarkerOptions().position(latLng));
        }

        hideSoftKeyboard();
    }

    private void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapActivity.this);
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else{
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length > 0){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    /*
        --------------------------- google places API autocomplete suggestions -----------------
     */

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if(!places.getStatus().isSuccess()){
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            final Place place = places.get(0);

            try{
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(TAG, "onResult: name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());
                Log.d(TAG, "onResult: address: " + place.getAddress());
//                mPlace.setAttributions(place.getAttributions().toString());
//                Log.d(TAG, "onResult: attributions: " + place.getAttributions());
                mPlace.setId(place.getId());
                Log.d(TAG, "onResult: id:" + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(TAG, "onResult: latlng: " + place.getLatLng());
                mPlace.setRating(place.getRating());
                Log.d(TAG, "onResult: rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                Log.d(TAG, "onResult: phone number: " + place.getPhoneNumber());
                mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(TAG, "onResult: website uri: " + place.getWebsiteUri());

                Log.d(TAG, "onResult: place: " + mPlace.toString());
            }catch (NullPointerException e){
                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage() );
            }

            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace);

            places.release();
        }
    };
}












