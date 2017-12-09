package mike.magic.com.brofinder;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Christoffer on 05-12-2017.
 */

public class EventUpdater {

    private Activity activity;
    private ListView eventListView;
    private ArrayList<Event> eventArray;

    public EventUpdater(Activity activity, ListView eventListView) {
        this.activity = activity;
        this.eventListView = eventListView;
        eventArray = new ArrayList<>();
    }

    public void addEvent(Event event) {
        if(event != null) {
            eventArray.add(event);
            //need a custom list adapter and notify changes
        }
    }

    public void updateEvent(Event event) {
        if(event != null) {
            for (Event item : eventArray) {
                if(item.getId() != null) {
                    if(item.getId().equals(event.getId())) {
                        //found correct event
                        //do the stuff that changes the data here
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
                    }
                }
            }
        }
    }
}