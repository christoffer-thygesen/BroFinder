package mike.magic.com.brofinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class CustomCommentAdapter extends ArrayAdapter<Comment> {
    CustomCommentAdapter(@NonNull Context context, CommentList commentz) {  //change
        super(context, R.layout.custom_row_for_comment , commentz.getCommentList());  //change
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater Evinflater = LayoutInflater.from(getContext());
        View customView = Evinflater.inflate(R.layout.custom_row_for_comment, parent, false);

        Comment itemComment = getItem(position);
        TextView textComment = (TextView) customView.findViewById(R.id.CCommentID);
        TextView textCreator = (TextView) customView.findViewById(R.id.CCreatorID);



        return customView;
    }
}
