package mike.magic.com.brofinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentList implements Serializable {

    private String id;
    private List<Comment> commentList;

    public CommentList() {}

    public CommentList(String id, ArrayList<Comment> commentList){
        this.id = id;
        this.commentList = commentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
