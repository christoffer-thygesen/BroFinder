package mike.magic.com.brofinder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EventAdapter extends ArrayAdapter<Event> {

    private ArrayList<Event> dataset;
    private Activity activity;



    public EventAdapter(Activity activity, ArrayList<Event> data) {
        super(activity, R.layout.eventlist, data);
        this.dataset = data;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Event event = getItem(position);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.eventlist, parent, false);

        TextView textTitle = (TextView)rowView.findViewById(R.id.eventTitle);
        TextView textDescription = (TextView)rowView.findViewById(R.id.eventDescription);
        TextView textDate = (TextView)rowView.findViewById(R.id.eventDate);
        TextView clockText = (TextView)rowView.findViewById(R.id.eventTime);
        TextView textLocation = (TextView)rowView.findViewById(R.id.eventLocation);
        TextView eventDistance = (TextView)rowView.findViewById(R.id.eventDistance);
        //does this even exist?
        //TextView textCreator = (TextView)rowView.findViewById(R.id.Creator);
        TextView participantsNumber = (TextView)rowView.findViewById(R.id.eventsParticipants);
        participantsNumber.setText("3");

        textTitle.setText(event.getTitle());
        textDescription.setText(event.getDesc());

        //Set DATE and TIME
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, event.getDay());
        calendar.set(Calendar.MONTH, event.getMonth());
        calendar.set(Calendar.YEAR, event.getYear());
        calendar.set(Calendar.HOUR_OF_DAY, event.getHour());
        calendar.set(Calendar.MINUTE, event.getMinute());
        String time = (new SimpleDateFormat("HH:mm").format(calendar.getTime()));
        String date = (new SimpleDateFormat("dd/MMM/yyyy").format(calendar.getTime()));
        textDate.setText(date);
        clockText.setText(time);
        textLocation.setText("I am location, until I get a better solution");
        String distance =  null;
        //textCreator.setText("BOIIII");

        return rowView;
    }
}