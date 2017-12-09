package mike.magic.com.brofinder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.location.places.Place;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

/**
 * Created by stoff on 01-12-2017.
 */

public class DatabaseManager implements ChildEventListener {

    private static DatabaseManager instance;
    private DatabaseReference databaseUsers;
    private DatabaseReference databaseEvents;
    private DatabaseReference databaseComments;
    private String application_id;
    private Context context;

    private EventUpdater eventUpdater;

    private DatabaseManager(Context context) {
        this.context = context;
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
        databaseUsers = firebaseDatabase.getReference("Users");
        databaseEvents = firebaseDatabase.getReference("Events");
        databaseComments = firebaseDatabase.getReference("Comments");

        databaseUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                User user = dataSnapshot.getValue(User.class);
                Log.d("TESTING", user.getId() + "");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseEvents.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Event event = dataSnapshot.getValue(Event.class);
                if(eventUpdater != null) {
                    eventUpdater.addEvent(event);
                } else {
                    //eventUpdater is not set yet
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Event event = dataSnapshot.getValue(Event.class);

                if(eventUpdater != null) {
                    eventUpdater.updateEvent(event);
                } else {
                    //eventupdater is not set yet
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Event event = dataSnapshot.getValue(Event.class);

                if(eventUpdater != null) {
                    eventUpdater.removeEvent(event);
                } else {
                    //eventupdater is not set yet
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseComments.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

    public void deleteUser(String userID) {
        databaseUsers.child(userID).removeValue();
    }

    //init this in main
    public void initialize(Activity activity, ListView eventListView) {
        eventUpdater = new EventUpdater(activity, eventListView);
    }

    public void addEvent(String title, String desc, String creator,
                         Calendar calendar, Place location) {

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        double lat = location.getLatLng().latitude;
        double lng = location.getLatLng().longitude;
        Event event = new Event(createEventID(), title, desc, createCommentID(),
                creator, day, month, year, hour, minute, lat, lng);

        databaseEvents.child(createEventID()).setValue(event);
    }

    public void destroy() {
        databaseComments.removeEventListener(this);
        databaseEvents.removeEventListener(this);
        databaseUsers.removeEventListener(this);
    }

    public void deleteEvent(Event event) {
        databaseEvents.child(event.getId()).removeValue();
    }

    public String createEventID()
    {
        return databaseEvents.push().getKey();
    }
    public String createCommentID()
    {
        return databaseComments.push().getKey();
    }

    //region UselessListenersButHasToBeThere
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    //endregionBut
}
