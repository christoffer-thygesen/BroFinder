package mike.magic.com.brofinder;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;


import java.util.ArrayList;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Activity context, ArrayList<Event> eventlisted){
        super(context,0,eventlisted);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater eventInflater = LayoutInflater.from(getContext());
        View eventCustomView = eventInflater.inflate(R.layout.eventlist,parent,false);

        final Event currentEvent = getItem(position);

        final TextView textTitle = (TextView)eventCustomView.findViewById(R.id.eventTitle);
        textTitle.setText("this is title");//Event.getTitle();

        final TextView textDescription = (TextView)eventCustomView.findViewById(R.id.eventDescription);
        textDescription.setText("This is description");//Event.getDesc());

        final TextView textDate = (TextView)eventCustomView.findViewById(R.id.eventDate);
        String date = "29/01\n2018"; //Event.getDay() + Event.getMonth() + Event.getYear();
        textDate.setText(date);

        final TextView clockText = (TextView)eventCustomView.findViewById(R.id.eventTime);
        clockText.setText("10:00"); //Event.getTimestamp());

        final TextView textLocation = (TextView)eventCustomView.findViewById(R.id.eventLocation);
        textLocation.setText("1313");//Event.getLocation_Lat() + " " + Event.getLocation_Lng());

        final TextView creator = (TextView)eventCustomView.findViewById(R.id.Creator);
        textLocation.setText("myself");//Event.getCreator();


        final TextView participantsNumber = (TextView)eventCustomView.findViewById(R.id.eventsParticipants);

        participantsNumber.setText("3");

        return eventCustomView;

    }
}