package mike.magic.com.brofinder;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Christoffer on 11-12-2017.
 */

public class CommentUpdater {

    private Activity activity;
    private ArrayList<CommentList> commentsArray;

    public CommentUpdater(Activity activity) {
        this.activity = activity;
        commentsArray = new ArrayList<>();
    }

    public void addComment(CommentList commentList) {
        if(commentList != null) {
            commentsArray.add(commentList);
        } else {
            //commentsUpdater not set
        }
    }
}
