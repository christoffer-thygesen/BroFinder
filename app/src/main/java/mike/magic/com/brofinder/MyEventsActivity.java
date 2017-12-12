package mike.magic.com.brofinder;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyEventsActivity extends AppCompatActivity {

    final ArrayList<Event> myEventsList = new ArrayList<>();
    DatabaseManager databaseManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);

        android.support.v7.widget.Toolbar brobar = (Toolbar) findViewById(R.id.toptoolbar);
        setSupportActionBar(brobar);

        ActionBar broActionBar = getSupportActionBar();
        broActionBar.setDisplayHomeAsUpEnabled(true);

        final ListView myEvents = findViewById(R.id.MyEventList);
        ListView participatingEvents = findViewById(R.id.myParticipatingEvents);

        final MyEventsAdapter myAdapter = new MyEventsAdapter(this, myEventsList);
        final MyEventsAdapter myParticipatingEventsAdapter = new MyEventsAdapter(this, myEventsList);

        Event a = new Event("123", "Kicking a ball", "Football", "abc", 5, 5, 2, 2017, 06022017, 333.1, 444.2);
        Event b = new Event("123", "ball", "ball", "abc", 5, 5, 2, 2017, 06022017, 333.1, 444.2);
        Event c = new Event("123", "Kicking", "Foot", "abc", 5, 5, 2, 2017, 06022017, 333.1, 444.2);
        myEventsList.add(0, a);
        myEventsList.add(1, b);
        myEventsList.add(2, c);

        CheckBox box = findViewById(R.id.checkboxAll);

        myEvents.setAdapter(myAdapter);

        participatingEvents.setAdapter(myParticipatingEventsAdapter);

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true){
                    for(int i = 0; i<myEvents.getChildCount();i++){
                        View mChild = myEvents.getChildAt(i);

                        CheckBox checkitOff = mChild.findViewById(R.id.checkBox);
                        checkitOff.setChecked(true);
                    }}
                else if(isChecked != true){
                    for(int i = 0; i<myEvents.getChildCount();i++){
                        View mChild = myEvents.getChildAt(i);

                        CheckBox checkitOff = mChild.findViewById(R.id.checkBox);
                        checkitOff.setChecked(false);
                    }
                }}
        });}


    public void refreshButton(View v){

        recreate();
        Toast.makeText( MyEventsActivity.this, "Refreshed page",
                Toast.LENGTH_SHORT).show();

    }

    public void deleteEvents(View v, boolean isChecked){
        /* for(int i = 0; i<myEventsList.size();i++){
            if(myEventsList.isChecked){
              //delete event

            }
        }*/
    }


}
