package mike.magic.com.brofinder;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailedActivity extends AppCompatActivity {

    public String eventId;
    private DatabaseManager databaseManager = DatabaseManager.getInstance(this);
    ArrayList<Event> eventArrayList = databaseManager.getEventUpdater().getEventArray();
    Event currentEvent;
    final Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Bundle bundle = getIntent().getExtras();
        eventId = bundle.getString("eventPosition");

        for (int i = 0; i < eventArrayList.size(); i++){
          if  (eventArrayList.get(i).getId() == eventId)
                    currentEvent = eventArrayList.get(i);
        }

        final TextView textTitle = (TextView) findViewById(R.id.eventDescription);
        textTitle.setText(currentEvent.getDesc());//Event.getTitle();









        // components from main.xml
        Button button = (Button) findViewById(R.id.ButtonForComment);
        //result = (EditText) findViewById(R.id.editTextResult);
        //we remove result, cuz we don't need it
        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get add_comment.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View addCommentView = li.inflate(R.layout.add_comment, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(addCommentView);

                final EditText userInput = (EditText) addCommentView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                     //   result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });



    }
    }


