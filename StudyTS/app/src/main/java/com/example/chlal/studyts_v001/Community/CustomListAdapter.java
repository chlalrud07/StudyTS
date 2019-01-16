package com.example.chlal.studyts_v001.Community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chlal.studyts_v001.R;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Post> postArray;

    public CustomListAdapter(Context context, ArrayList<Post> postArray) {
        this.context = context;
        this.postArray = postArray;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.post_item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Post currentItem = (Post) getItem(position);
        viewHolder.postTitle.setText(currentItem.getTitle());
        viewHolder.postDetail.setText(currentItem.getDetail());

        return convertView;
    }
    private class ViewHolder {
        TextView postTitle;
        TextView postDetail;

        ViewHolder(View view) {
            postTitle = view.findViewById(R.id.postTitle);
            postDetail = view.findViewById(R.id.postDetail);
        }
    }
    @Override
    public int getCount() {return postArray.size();}

    @Override
    public Object getItem(int position) {return postArray.get(position);}

    @Override
    public long getItemId(int position) {return position;}

}
