package mike.magic.com.brofinder;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Christoffer on 09-12-2017.
 */

public class EventEventListener implements ChildEventListener {

    private EventUpdater eventUpdater;

    public EventEventListener(EventUpdater eventUpdater) {
        this.eventUpdater = eventUpdater;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Event event = dataSnapshot.getValue(Event.class);
        Log.d("EVENTTEST", dataSnapshot.toString());

        if(eventUpdater != null) {
            eventUpdater.addEvent(event);
        } else {
            //eventUpdater is not set and fails
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        Event event = dataSnapshot.getValue(Event.class);

        if(eventUpdater != null) {
            eventUpdater.updateEvent(event);
        } else {
            //eventupdater not set
        }
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        Event event = dataSnapshot.getValue(Event.class);

        if(eventUpdater != null) {
            eventUpdater.removeEvent(event);
        } else {
            //eventUpdater not set
        }
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
