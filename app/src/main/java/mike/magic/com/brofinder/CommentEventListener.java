package mike.magic.com.brofinder;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christoffer on 09-12-2017.
 */

public class CommentEventListener implements ChildEventListener {

    private CommentUpdater commentUpdater;

    public CommentEventListener(CommentUpdater commentUpdater) {
        this.commentUpdater = commentUpdater;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        //CommentList commentList = dataSnapshot.getValue(CommentList.class);
        Log.d("DATASNAPSHOT", dataSnapshot.toString());
        Object obj = dataSnapshot.getValue();
        Log.d("COMMENTTEST", obj.toString());
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
