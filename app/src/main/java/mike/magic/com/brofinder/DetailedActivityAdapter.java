package mike.magic.com.brofinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class DetailedActivityAdapter extends ArrayAdapter<String> {
    DetailedActivityAdapter(@NonNull Context context, String[] eventz) {  //change
        super(context, R.layout.custom_row_for_event , eventz);  //change
    }


}
