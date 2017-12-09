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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyEventsAdapter extends ArrayAdapter<Event> {

    public MyEventsAdapter(Activity context, ArrayList<Event> myEvents){
        super(context,0, myEvents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater myEventInflater = LayoutInflater.from(getContext());
        View myEventCustomView = myEventInflater.inflate(R.layout.myeventlist,parent,false);

        final Event currentEvent = getItem(position);

        final TextView textTitle = (TextView)myEventCustomView.findViewById(R.id.myEventsTitle);
        textTitle.setText("this is title");//Event.getTitle();

        final TextView textDescription = (TextView)myEventCustomView.findViewById(R.id.myEventsDescription);
        textDescription.setText("This is description");//Event.getDesc());

        final TextView textDate = (TextView)myEventCustomView.findViewById(R.id.myEventsDate);
        String date = "29/01\n2018"; //Event.getDay() + Event.getMonth() + Event.getYear();
        textDate.setText(date);

        final TextClock clockText = (TextClock)myEventCustomView.findViewById(R.id.myEventsTimestamp);
        clockText.setText("10:00"); //Event.getTimestamp());

        final TextView textLocation = (TextView)myEventCustomView.findViewById(R.id.myEventsLocation);
        textLocation.setText("1313");//Event.getLocation_Lat() + " " + Event.getLocation_Lng());

        final TextView creator = (TextView)myEventCustomView.findViewById(R.id.Creator);
        textLocation.setText("myself");//Event.getCreator();


        final TextView commentsNumber = (TextView)myEventCustomView.findViewById(R.id.MyEventsNumberOfComments);

        commentsNumber.setText("1");

        final TextView participantsNumber = (TextView)myEventCustomView.findViewById(R.id.MyEventsNumberOfParticipants);

        participantsNumber.setText("3");

        return myEventCustomView;

    }
}