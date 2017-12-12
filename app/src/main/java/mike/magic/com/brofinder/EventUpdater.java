package mike.magic.com.brofinder;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Christoffer on 05-12-2017.
 */

public class EventUpdater {

    private Activity activity;
    private ListView eventListView;
    private EventAdapter eventAdapter;
    private ArrayList<Event> eventArray;
    private GoogleApiClient googleApiClient;

    public EventUpdater(Activity activity, final ListView eventListView, GoogleApiClient googleApiClient) {
        this.activity = activity;
        this.eventListView = eventListView;
        this.googleApiClient = googleApiClient;
        eventArray = new ArrayList<>();
        eventAdapter = new EventAdapter(activity, eventArray);
        eventListView.setAdapter(eventAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //  Log.d("test", "Test");
                Intent myIntent = new Intent(eventListView.getContext(), DetailedActivity.class);
                String nameOfString = String.valueOf(adapterView.getItemAtPosition(position));
                myIntent.putExtra("eventName", position); // not position, something else
                eventListView.getContext().startActivity(myIntent);

            }
        });
    }

    public Location getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return null;
        }
        Location broLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        return broLocation;
    }

    public Event getDistance(Event item) {
        Location myLocation = getCurrentLocation();
        if(myLocation == null) {
            //you're fucked
        } else {

            Location eventLocation2;
            eventLocation2 = new Location("eventLocation");
            eventLocation2.setLatitude(item.getLocation_Lat());
            eventLocation2.setLongitude(item.getLocation_Lng());

            double local = myLocation.distanceTo(eventLocation2);
            int local2 = (int) Math.ceil(local / 1000);
            Log.d("AAAAAAAAAAAAA", local2 + "");
            item.setDistance(local2);
        }
        return item;


//        for(Event item: eventArray){
//            Log.d("AAAAAAAAAAAAAAAAAAAAAA", item.getId());
//            Location eventLocation2;
//            eventLocation2 = new Location("eventLocation");
//            eventLocation2.setLatitude(item.getLocation_Lat());
//            eventLocation2.setLongitude(item.getLocation_Lng());
//            double local = broLocation.distanceTo(eventLocation2);
//            int local2 = (int) Math.round(local);
//            item.setDistance(local2);
//            Log.d("AAAAAAAAAAAAAAAAAAAAAA", local2 + "");
//        }
    }

    public void addEvent(Event event) {
        if(event != null) {
            eventArray.add(event);
            eventAdapter.notifyDataSetChanged();
        }
    }

    public void updateEvent(Event event) {
        if(event != null) {
            for (Event item : eventArray) {
                if(item.getId() != null) {
                    if(item.getId().equals(event.getId())) {
                        //found correct event
                        //do the stuff that changes the data here
                        item.setId(event.getId());
                        item.setTitle(event.getTitle());
                        item.setDesc(event.getDesc());
                        item.setCreator(event.getCreator());
                        item.setDay(event.getDay());
                        item.setMonth(event.getMonth());
                        item.setYear(event.getYear());
                        item.setHour(event.getHour());
                        item.setMinute(event.getMinute());
                        item.setLocation_Lat(event.getLocation_Lat());
                        item.setLocation_Lng(event.getLocation_Lng());
                        item.setDistance(event.getDistance());
                    }
                }
            }
        }
    }

    public void removeEvent(Event event) {
        if(event != null) {
            for (Event item : eventArray) {
                if(item.getId() != null) {
                    if(item.getId().equals(event.getId())) {
                        //questionable method
                        eventArray.remove(event);
                        DatabaseManager.getInstance(activity).deleteEvent(event);
                        eventAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    public ArrayList<Event> getEventArray() {
        return eventArray;
    }
}