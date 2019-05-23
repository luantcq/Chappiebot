package com.example.chappiebot.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chappiebot.R;
import com.example.chappiebot.model.ContentType;
import com.example.chappiebot.model.Feed;

import java.util.ArrayList;

public class FeedsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Feed> arrayList;

    public FeedsAdapter(Context context, ArrayList<Feed> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Feed getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            holder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            holder.tvDetail = (TextView) convertView.findViewById(R.id.tvDetail);
            holder.imDetail = convertView.findViewById(R.id.imDetail);
            holder.imDetail1 = convertView.findViewById(R.id.imDetail1);
            holder.imDetail2 = convertView.findViewById(R.id.imDetail2);
            holder.imDetail3 = convertView.findViewById(R.id.imDetail3);

            holder.lnMultiPhoto = convertView.findViewById(R.id.lnMultiPhoto);
            convertView.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imDetail.setVisibility(View.GONE);
        holder.lnMultiPhoto.setVisibility(View.GONE);
        holder.tvTitle.setText(getItem(position).getTitle());
        if (getItem(position).getContent_type().equalsIgnoreCase(ContentType.OVERVIEW.toString())) {
            holder.imDetail.setVisibility(View.VISIBLE);
            Glide.with(context).load(getItem(position).getPublisher().getIcon()).into(holder.imDetail);
        } else if ((getItem(position).getContent_type().equalsIgnoreCase(ContentType.STORY.toString())
                || getItem(position).getContent_type().equalsIgnoreCase(ContentType.GALLERY.toString()))
                && null != getItem(position).getImages()
                && getItem(position).getImages().size() >= 3) {
            holder.lnMultiPhoto.setVisibility(View.VISIBLE);
            Glide.with(context).load(getItem(position).getImages().get(0).getHref()).into(holder.imDetail1);
            Glide.with(context).load(getItem(position).getImages().get(1).getHref()).into(holder.imDetail2);
            Glide.with(context).load(getItem(position).getImages().get(2).getHref()).into(holder.imDetail3);
        }
        return convertView;
    }

    private class ViewHolder {
        protected TextView tvTitle, tvDescription, tvDetail;
        protected ImageView imDetail, imDetail2, imDetail1, imDetail3;
        protected LinearLayout lnMultiPhoto;
    }

}