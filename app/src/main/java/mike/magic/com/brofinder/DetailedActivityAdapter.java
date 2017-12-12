package mike.magic.com.brofinder;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedActivityAdapter extends ArrayAdapter<Event> {

    public DetailedActivityAdapter(Activity context, ArrayList<Event> eventToTakeInfoFrom){
        super(context,0, eventToTakeInfoFrom);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater myEventInflater = LayoutInflater.from(getContext());
        View myEventCustomView = myEventInflater.inflate(R.layout.activity_detailed,parent,false);

        final Event currentEvent = getItem(position);

        final TextView textTitle = (TextView)myEventCustomView.findViewById(R.id.eventDescription);
        textTitle.setText(currentEvent.getDesc());//Event.getTitle();

        return myEventCustomView;
    }
}