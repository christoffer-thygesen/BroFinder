package mike.magic.com.brofinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

 class Comment implements Serializable {

     private String usernameID;
     private String comment;

     public Comment() {}

     public Comment(String usernameID, String comment) {
         this.usernameID = usernameID;
         this.comment = comment;
     }

     public String getUsernameID() {
         return usernameID;
     }

     public void setUsernameID(String usernameID) {
         this.usernameID = usernameID;
     }

     public String getComment() {
         return comment;
     }

     public void setComment(String comment) {
         this.comment = comment;
     }

     @Override
     public String toString() {
         return "Comment{" +
                 "usernameID='" + usernameID + '\'' +
                 ", comment='" + comment + '\'' +
                 '}';
     }

     public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("usernameID", usernameID);
        result.put("comment", comment);
        return result;
    }
}
