package mike.magic.com.brofinder;

import android.app.Activity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stoff on 13-12-2017.
 */

public class CommentAdapter extends ArrayAdapter<Comment> {

    private List<Comment> dataset;
    private Activity activity;

    public CommentAdapter(Activity activity, List<Comment> data) {
        super(activity, R.layout.custom_row_for_comment, data);
        this.dataset = data;
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Comment comment = getItem(position);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_row_for_comment, parent, false);

        TextView textCreator = rowView.findViewById(R.id.textCreator);
        TextView textComment = rowView.findViewById(R.id.textComment);

        ArrayList<User> userArrayList = DatabaseManager.getInstance(activity).getUserUpdater().getUserArray();
        String username = "name not in firebase";
        for (User user : userArrayList) {
            if(comment.getUsernameID().equals(user.getId())) {
                username = user.getUsername();
                break;
            }
        }

        SpannableString spanString = new SpannableString(username);
        spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);

        //maybe change ID to actual name instead.
        textCreator.setText(spanString);
        textComment.setText(comment.getComment());

        return rowView;
    }
}
