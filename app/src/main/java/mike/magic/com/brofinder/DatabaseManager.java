package mike.magic.com.brofinder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by stoff on 01-12-2017.
 */

public class DatabaseManager {

    private static DatabaseManager instance;
    private DatabaseReference databaseUsers;
    private DatabaseReference databaseEvents;
    private DatabaseReference databaseComments;
    private FirebaseDatabase firebaseDatabase;
    private String application_id;
    private Context context;

    private UserEventListener userEventListener;
    private EventEventListener eventEventListener;
    private CommentEventListener commentEventListener;

    private EventUpdater eventUpdater;
    private UserUpdater userUpdater;
    private CommentUpdater commentUpdater;

    private GoogleApiClient googleApiClient;

    private DatabaseManager(Context context) {
        this.context = context;
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        application_id = sharedPreferences.getString(context.getString(R.string.APPLICATION_ID), null);

        if(application_id == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            DatabaseReference root = firebaseDatabase.getReference();
            application_id = root.push().getKey();
            editor.putString(context.getString(R.string.APPLICATION_ID), application_id);
            editor.commit();
        }
        else {
            //already has app ID
        }
    }

    //init this in main
    public void initialize(Activity activity, ListView eventListView, GoogleApiClient googleApiClient) {
        //init updaters
        eventUpdater = new EventUpdater(activity, eventListView, googleApiClient);
        userUpdater = new UserUpdater(activity);
        commentUpdater = new CommentUpdater(activity);


        userEventListener = new UserEventListener(userUpdater);
        eventEventListener = new EventEventListener(eventUpdater);
        commentEventListener = new CommentEventListener(commentUpdater);

        databaseUsers = firebaseDatabase.getReference("Users");
        databaseEvents = firebaseDatabase.getReference("Events");
        databaseComments = firebaseDatabase.getReference("Comments");

        databaseUsers.addChildEventListener(userEventListener);
        databaseEvents.addChildEventListener(eventEventListener);
        databaseComments.addChildEventListener(commentEventListener);
    }

    //one of those singleton boi
    public static DatabaseManager getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    public void addUser(String userID, String username) {
        User user = new User(userID, username);
        databaseUsers.child(userID).setValue(user);
    }

    public void deleteUser(User user) {
        databaseUsers.child(user.getId()).removeValue();
    }

    public User getCurrentUser(String user) {
        User currentUser = new User();
        if(user != null) {
            for(User item : userUpdater.getUserArray()) {
                if(item.getId() != null) {
                    if(item.getId().equals(user)) {
                        currentUser = item;
                    }
                }
            }
        }
        return currentUser;
    }

    public boolean hasChildComments(Event event){
        return false;
    }

  /*  public ArrayList<User> getAllUsers(String user) {
        ArrayList<User> allOfDemUsers = new ArrayList<>();
        User currentUser = new User();
        if(user != null) {
            for(User item : userUpdater.getUserArray()) {
                if(item.getId() != null) {
                    if(item.getId().equals(user)) {
                        currentUser = item;
                    }
                }
            }
        }
        return allOfDemUsers;
    } */

    public void addEvent(String title, String desc, String creator,
                         Calendar calendar, Place location) {

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        double lat = location.getLatLng().latitude;
        double lng = location.getLatLng().longitude;
        String eventID = createEventID();





        Event event = new Event(eventID, title, desc, creator, day, month, year, hour, minute, lat, lng);

        databaseEvents.child(eventID).setValue(event);
    }

    public void destroy() {
        databaseComments.removeEventListener(commentEventListener);
        databaseEvents.removeEventListener(eventEventListener);
        databaseUsers.removeEventListener(userEventListener);
    }

    public EventUpdater getEventUpdater() {
        return eventUpdater;
    }

    public void deleteEvent(Event event) {
        databaseEvents.child(event.getId()).removeValue();
    }

    public String createEventID()
    {
        return databaseEvents.push().getKey();
    }

    public String distance(){
        return databaseEvents.push().toString();
    }
}
