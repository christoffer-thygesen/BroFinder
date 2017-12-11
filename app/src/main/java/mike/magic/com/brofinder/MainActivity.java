package mike.magic.com.brofinder;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.location.Criteria.ACCURACY_COARSE;
import static android.location.Criteria.ACCURACY_FINE;

public class MainActivity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener{

    private DatabaseManager databaseManager;
    private android.support.v7.widget.Toolbar brobar;
    private ListView eventListView;
    private SeekBar searchRadius;
    private TextView searchRadiusText;
    private GoogleApiClient broGoogleApiClient;
    private LocationManager locationManager; //NEEDED?
    //temp longtitude and latitude text fields
    private LocationListener broListener;
    private TextView myLongtitude;
    private TextView myLatitude;
    double myLat;
    double myLong;
    public String broProvider;
    String TAG = "GoogleMapsAPI";

    //https://stackoverflow.com/questions/42744977/onlocationchanged-getting-called-once-android-studio --use for setting onLocationUpdate

    //https://www.mytrendin.com/display-location-save-firebase-database/  how to store location in firebase --use for reading/writing to database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        //Request location from GPS with priority High Accuracy. Initial location
        LocationRequest broLocationRequest = new LocationRequest();
        broLocationRequest.setInterval(100);
        broLocationRequest.setFastestInterval(101);
        broLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //Activate locationListener to listen for onLocationChanged
        broListener = new LocationListener(){
            @Override
            public void onLocationChanged(Location location) {
                myLong = location.getLongitude();
                myLat = location.getLatitude();
                myLatitude.setText("Latitude: " + myLat);
                myLongtitude.setText("Longtitude: " + myLong);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            //no method for status changed
            }

            @Override
            public void onProviderEnabled(String s) {
            //no method for provider enabled
            }

            @Override
            public void onProviderDisabled(String s) {
            //if GPS is turned off, go to turn on GPS intent
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };


        eventListView = findViewById(R.id.eventList);
        //Get the list from EventUpdater
        //final ArrayList<Event> eventsListing = new ArrayList<>();
        //final EventAdapter myAdapter2 = new EventAdapter(this, eventsListing);

        //init databaseManager and updaters
        //LET THIS INIT FIRST
        databaseManager = DatabaseManager.getInstance(this);
        databaseManager.initialize(MainActivity.this, eventListView);

        //initialize long + lat textviews

        buildGoogleAPIClient();

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
               android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            //Location broLocation = LocationServices.FusedLocationApi.getLastLocation(broGoogleApiClient);
            locationManager.requestLocationUpdates("gps", 5000, 0, broListener);
           // myLong = String.valueOf(broLocation.getLongitude());
            myLatitude = findViewById(R.id.myLatitude);
            myLongtitude = findViewById(R.id.myLongtitude);
            myLatitude.setText("Latitude: " + myLat);
            myLongtitude.setText("Longtitude: " + myLong);

            Toast.makeText( MainActivity.this, "GPS is on!",
                    Toast.LENGTH_SHORT).show();
        }

        //initializes seekbar with units and seekbar.onchangelistener
        initializeSeekbar();

        android.support.v7.widget.Toolbar brobar = (Toolbar)findViewById(R.id.toptoolbar);
        setSupportActionBar(brobar);

        ActionBar broActionBar = getSupportActionBar();
        broActionBar.setDisplayHomeAsUpEnabled(true);

        //setting Search Radius Seekbar
        searchRadius.setProgress(10); //default value 10 km
        searchRadiusText.setText("Distance " + searchRadius.getProgress() + " Km");
        searchRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 10; //default value 10 km

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                searchRadiusText.setText("Distance " + progress + " km");
        }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                searchRadiusText.setText("Distance " + progress + " km");
                //EventUpdater.EventUpdater(this,eventListView);

                    //Extra function to ensure check of Location. This way we can compare userLocation with EventLocation
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates("gps", 5000, 0, broListener);
                        Location broLocation = LocationServices.FusedLocationApi.getLastLocation(broGoogleApiClient);
                        LocationRequest broLocationRequest = new LocationRequest();
                        broLocationRequest.setInterval(100);
                        broLocationRequest.setFastestInterval(101);
                        broLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

                        ArrayList<Event> eventCopyList = databaseManager.getEventUpdater().getEventArray();

                        for(Event item: eventCopyList){
                            Location eventLocation;
                            eventLocation = new Location("eventLocation");
                            eventLocation.setLatitude(item.getLocation_Lat());
                            eventLocation.setLongitude(item.getLocation_Lng());



                            if(broLocation.distanceTo(eventLocation) <= progress){

                            }



                        }
            }
            }});
    }

        @Override
        protected void onStart(){
        super.onStart();
        //onStart connect googleAPIclient
        broGoogleApiClient.connect();
        }

        @Override
        protected void onStop() {
        super.onStop();
        //onStop disconnect googleAPIclient
        if(broGoogleApiClient.isConnected()) {
            broGoogleApiClient.disconnect();
        }
        }

        @Override
        public void onConnected(Bundle connectionHint) {
            //check if permission is set

            LocationRequest broLocationRequest = new LocationRequest();
            broLocationRequest.setInterval(100);
            broLocationRequest.setFastestInterval(101);
            broLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            if(ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            //    LocationServices.FusedLocationApi.requestLocationUpdates(broGoogleApiClient, broLocationRequest, (com.google.android.gms.location.LocationListener) this);
                Toast.makeText( MainActivity.this, "Turn on GPS or Grant permission!",
                        Toast.LENGTH_SHORT).show();

            }
            //if permission is set, set longtitude and latitude
          if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Location broLastLocation = LocationServices.FusedLocationApi.getLastLocation(broGoogleApiClient);


                if (broLastLocation != null) {
                    myLat = broLastLocation.getLatitude();
                    myLong = broLastLocation.getLongitude();
                    myLatitude.setText("Latitude: " + myLat);
                    myLongtitude.setText("Longtitude: " + myLong);
                }
        }}

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "Connection suspended");
        broGoogleApiClient.connect();
    }

        @Override
        public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() =" + result.getErrorCode());
        }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this,MyProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.myevents_action:
                Intent intent2 = new Intent(this,MyEventsActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void tologin(View v){
        goToLoginPage();
    }


    public void goToLoginPage() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void toCreateEvent(View view) {
        Intent intent = new Intent(this,CreateEventActivity.class);
        startActivity(intent);
    }

    public void initializeSeekbar(){
        searchRadius = findViewById(R.id.searchRadius);
        searchRadiusText = findViewById(R.id.searchRadiusUnits);
    }


public synchronized void buildGoogleAPIClient(){
        broGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
            }

    public void getDistanceShow(Location broLocation, Location eventLocation,int progress){
    //for(int i = 0; i<allevents;i++){}
    if(broLocation.distanceTo(eventLocation)<=progress){
        //show on list Event[i]
    }
    }
        }