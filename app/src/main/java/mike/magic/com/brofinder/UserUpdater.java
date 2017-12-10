package mike.magic.com.brofinder;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by stoff on 05-12-2017.
 */

public class UserUpdater {

    private Activity activity;
    private ArrayList<User> userArray;

    public UserUpdater(Activity activity) {
        this.activity = activity;
        userArray = new ArrayList<>();
    }

    public void addUser(User user) {
        if(user != null) {
            userArray.add(user);
            Log.d("USERTEST", userArray.size() + "");
        } else {
            //user is not set
        }
    }

    public void removeUser(User user) {
        if(user != null) {
            for(User item : userArray) {
                if(item.getId() != null) {
                    if(item.getId().equals(user.getId())) {
                        userArray.remove(user);
                        DatabaseManager.getInstance(activity).deleteUser(user);
                    }
                }
            }
        }
    }
}