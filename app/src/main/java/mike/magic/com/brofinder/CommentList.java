package mike.magic.com.brofinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.List;

public class CommentList implements Serializable {

    private String commentListID;
    private List<Comment> commentList;

    public CommentList(String commentListID, List<Comment> commentList) {
        this.commentListID = commentListID;
        this.commentList = commentList;
    }

    public String getCommentListID() {
        return commentListID;
    }

    public void setCommentListID(String commentListID) {
        this.commentListID = commentListID;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "CommentList{" +
                "commentListID='" + commentListID + '\'' +
                ", commentList=" + commentList +
                '}';
    }


}
