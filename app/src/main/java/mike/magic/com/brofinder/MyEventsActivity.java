package mike.magic.com.brofinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyEventsActivity extends AppCompatActivity {
    /*
        final ArrayList<String> myEventList = new ArrayList<String>();
        final ArrayAdapter myEventAdapter = new ArrayAdapter(this,R.layout.myeventlist , myEventList);
        ListView listView = (ListView)findViewById(R.id.list);
        //listView.setAdapter(myEventAdapter);

    */


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);


        final ListView myEvents = findViewById(R.id.MyEventList);
        ListView participatingEvents = findViewById(R.id.myParticipatingEvents);
        final ArrayList<Event> myEventsList = new ArrayList<>();
        final MyEventsAdapter myAdapter = new MyEventsAdapter(this, myEventsList);
        final MyEventsAdapter myParticipatingEventsAdapter = new MyEventsAdapter(this, myEventsList);


//        Event a = new Event("123", "Kicking a ball", "Football", "abc", "John", 5, 2, 2017, 06022017, 333, 444, 555);
//        Event b = new Event("1234", "Kicking a bottle", "Beer", "ab", "Jo", 6, 2, 2017, 06022017, 333, 444, 555);
//        Event c = new Event("1235", "Kicking ", "Walk", "abcd", "Johnathan", 5, 3, 2017, 06022017, 333, 444, 555);
//        myEventsList.add(0, a);
//        myEventsList.add(1, b);
//        myEventsList.add(2, c);

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



        /*
       for(Event myEvents : myEventList) {
   if(Event.getId(creatorID).equals(someId) {
   ListView myEvents = (ListView)findViewById(R.id.myEventList);
       //found it!
   }
}

for(Event myParticipatingEvents : myEventList)(
if(Event.getId( *id = participating*?){
ListView participatingEvents = (ListView)findViewById(R.id.myParticipatingEvents);
        * */





    public void refreshButton(View v){

        recreate();
        Toast.makeText( MyEventsActivity.this, "Refreshed page",
                Toast.LENGTH_SHORT).show();

    }

    public void deleteEvents(View v){
        /*for(int i = 0; i<myEventsList.getCount();i++){
            if(myEventsList.isChecked){
               DatabaseManager.getInstance(.deleteEvent();)
               EventUpdater.removeEvent(event[i]);
            }
        }*/
    }


}
