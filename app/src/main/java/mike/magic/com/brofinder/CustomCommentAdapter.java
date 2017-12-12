package mike.magic.com.brofinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomCommentAdapter extends ArrayAdapter<Comment> {

    public CustomCommentAdapter(@NonNull Context context, CommentList LotsAComments) {
        super(context, R.layout.custom_row_for_comment, LotsAComments.getCommentList());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater commentInflater = LayoutInflater.from(getContext());
        View myEventCustomView = commentInflater.inflate(R.layout.custom_row_for_comment,parent,false);

        final Comment itemComment = getItem(position);

        TextView textComment = (TextView)myEventCustomView.findViewById(R.id.CCommentID);
        textComment.setText(itemComment.getComment());
        TextView textCreatorOfComment = (TextView)myEventCustomView.findViewById(R.id.CCreatorID);
        textCreatorOfComment.setText(itemComment.getUsernameID());


        return myEventCustomView;

    }

}
