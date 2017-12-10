package mike.magic.com.brofinder;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by Christoffer on 09-12-2017.
 */

public class UserEventListener implements ChildEventListener {

    private UserUpdater userUpdater;

    public UserEventListener(UserUpdater userUpdater) {
        this.userUpdater = userUpdater;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        User user = dataSnapshot.getValue(User.class);

        if(userUpdater != null) {
            userUpdater.addUser(user);
        } else {
            //userUpdater not set
        }
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
