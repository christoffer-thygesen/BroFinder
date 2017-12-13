package mike.magic.com.brofinder;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DetailedActivity extends AppCompatActivity {


    private Event currentEvent;
    private Context context = this;
    private Comment result; //user's comment that we have to save in the database;
    private Toolbar toolbar;
    private TextView desc;
    private TextView location;
    private TextView textviewDate;
    private TextView creator;
    private Button createComment;
    private DatabaseManager databaseManager;
    private CommentAdapter commentAdapter;
    private List<Comment> commentArrayList;
    private ListView commentListView;


    private void loadActivity() {
        setContentView(R.layout.activity_detailed);

        //get event clicked on
        final Bundle bundle = getIntent().getExtras();
        currentEvent = (Event) bundle.getSerializable("eventID");
        databaseManager = DatabaseManager.getInstance(DetailedActivity.this);

        toolbar = (Toolbar) findViewById(R.id.toptoolbar);
        desc = findViewById(R.id.eventDescription);
        location = findViewById(R.id.eventPlace);
        textviewDate = findViewById(R.id.eventTime);
        creator = findViewById(R.id.eventCreator);
        createComment = findViewById(R.id.buttonForComment);
        commentListView = findViewById(R.id.CommentListView);

        toolbar.setTitle(currentEvent.getTitle());
        desc.setText(currentEvent.getDesc());
        location.setText("Lat: " + currentEvent.getLocation_Lat() + " Lng: " + currentEvent.getLocation_Lng());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, currentEvent.getDay());
        calendar.set(Calendar.MONTH, currentEvent.getMonth());
        calendar.set(Calendar.YEAR, currentEvent.getYear());
        calendar.set(Calendar.HOUR_OF_DAY, currentEvent.getHour());
        calendar.set(Calendar.MINUTE, currentEvent.getMinute());

        String time = ("Time: " + new SimpleDateFormat("HH:mm").format(calendar.getTime()));
        String date = ("Date: " + new SimpleDateFormat("dd/MMM/yyyy").format(calendar.getTime()));
        textviewDate.setText(date + " " + time);

        ArrayList<User> userArrayList = databaseManager.getUserUpdater().getUserArray();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        for (User user : userArrayList) {
            if(uid.equals(user.getId())) {
                uid = user.getUsername();
                break;
            }
        }
        creator.setText(uid);

        commentArrayList = currentEvent.getCommentList();
        commentAdapter = new CommentAdapter(DetailedActivity.this, commentArrayList);
        commentListView.setAdapter(commentAdapter);

        createComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // get add_comment.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View addCommentView = li.inflate(R.layout.add_comment, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(addCommentView);

                final EditText userInput = addCommentView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //   result.setText(userInput.getText());
                                        //String commentTextFromUser = "";
                                        //commentTextFromUser
                                        result = new Comment(FirebaseAuth.getInstance().getCurrentUser().getUid(), (userInput.getText()).toString());
                                        databaseManager.addComment(result, currentEvent.getId());
                                        finish();
                                        //commentAdapter.notifyDataSetChanged();
                                        //CheckForAndAddComment(result, FirebaseAuth.getInstance().getCurrentUser().getUid());

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadActivity();
    }

    //    public void creatingTehComments(CommentList LotsAcomments){
//        ListAdapter commmentsAdapter = new CustomCommentAdapter(this, LotsAcomments);
//        ListView commentListView = findViewById(R.id.CommentListView);
//        commentListView.setAdapter(commmentsAdapter);
//    }
//    public void creatingTehComments(String strnk, ArrayList<Comment> arrayListWithComments){
//        CommentList LotsAcomments = new CommentList(strnk, arrayListWithComments);
//        ListAdapter commmentsAdapter = new CustomCommentAdapter(this, LotsAcomments);
//        ListView commentListView = findViewById(R.id.CommentListView);
//        commentListView.setAdapter(commmentsAdapter);
//    }
//
//    public void CheckForAndAddComment(Comment comment, String currentUserUsername){
//       // currentEvent.
//
//
//    }


}


