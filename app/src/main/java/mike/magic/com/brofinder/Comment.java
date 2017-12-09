package mike.magic.com.brofinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Comment implements Serializable {

    private String commentID;
    private String commentText;
    private int commenterID;
    private int commentOrderID;


    public Comment() {}

    public Comment(String commentID, String commentText, int commenterID, int commentOrderID){
        this.commentID = commentID;
        this.commentText = commentText;
        this.commenterID = commenterID;
        this.commentOrderID = commentOrderID;
    }


    public String getCommentID() {return commentID;}

    public String getCommentText() {return commentText;}

    public int getCommenterID() {return commenterID;}

    public void setCommentID(String commentID) {this.commentID = commentID;}

    public void setCommentText(String commentText) {this.commentText = commentText;}

    public void setCommenterID(int commenterID) {this.commenterID = commenterID;}

    public int getCommentOrderID() {
        return commentOrderID;
    }

    public void setCommentOrderID(int commentOrderID) {
        this.commentOrderID = commentOrderID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", commentText='" + commentText + '\'' +
                ", commenterID=" + commenterID +
                ", commenterID=" + commentOrderID +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("commentID", commentID);
        result.put("commentText", commentText);
        result.put("commenterID", commenterID);
        result.put("commenterID", commentOrderID);
        return result;
    }
}
