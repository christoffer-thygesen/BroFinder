package mike.magic.com.brofinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailedActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Bundle bundle = getIntent().getExtras();
        int tempPosition = bundle.getInt("eventName");
        //Event event = getItem(tempPosition);

        TextView textView = findViewById(R.id.eventDescription);
        //textView.setText();
    }

}
