package com.example.chlal.studyts_v001.Study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chlal.studyts_v001.Community.Post;
import com.example.chlal.studyts_v001.R;
import com.example.chlal.studyts_v001.Study.Item;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> itemArray;

    public CustomListAdapter(Context context, ArrayList<Item> itemArray) {
        this.context = context;
        this.itemArray = itemArray;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.study_item_layout, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Item currentItem = (Item) getItem(position);
        viewHolder.postTitle.setText(currentItem.getTitle());
        viewHolder.postDetail.setText(currentItem.getDetail());

        return convertView;
    }
    private class ViewHolder {
        TextView postTitle;
        TextView postDetail;

        ViewHolder(View view) {
            postTitle = view.findViewById(R.id.studyTitle);
            postDetail = view.findViewById(R.id.studyDetail);
        }
    }
    public String getPostID(int position) {
        return this.itemArray.get(position).getPid();
    }
    @Override
    public int getCount() {return itemArray.size();}

    @Override
    public Object getItem(int position) {return itemArray.get(position);}

    @Override
    public long getItemId(int position) {return position;}

}
