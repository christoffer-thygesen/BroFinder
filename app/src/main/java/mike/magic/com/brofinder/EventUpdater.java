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
    private EventAdapter eventAdapter;
    private ArrayList<Event> eventArray;

    public EventUpdater(Activity activity, ListView eventListView) {
        this.activity = activity;
        this.eventListView = eventListView;
        eventArray = new ArrayList<>();
        eventAdapter = new EventAdapter(activity, eventArray);
        eventListView.setAdapter(eventAdapter);
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
                        item.setCommentsID(event.getCommentsID());
                        item.setCreator(event.getCreator());
                        item.setDay(event.getDay());
                        item.setMonth(event.getMonth());
                        item.setYear(event.getYear());
                        item.setHour(event.getHour());
                        item.setMinute(event.getMinute());
                        item.setLocation_Lat(event.getLocation_Lat());
                        item.setLocation_Lng(event.getLocation_Lng());
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