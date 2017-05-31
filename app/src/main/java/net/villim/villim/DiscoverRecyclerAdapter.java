package net.villim.villim;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by seongmin on 5/26/17.
 */

public class DiscoverRecyclerAdapter extends RecyclerView.Adapter<DiscoverRecyclerAdapter.ViewHolder> {
    private VillimRoom[] roomList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView roomThumbnail;
        public TextView roomTitle;
        public TextView roomPriceValue;
        public RatingBar roomRatingBar;
        public TextView roomRatingCount;


        public ViewHolder(View v) {
            super(v);
            roomThumbnail = (ImageView) v.findViewById(R.id.discover_room_thumbnail);
            roomTitle = (TextView) v.findViewById(R.id.discover_room_title);
            roomPriceValue = (TextView) v.findViewById(R.id.discover_room_price_value);
            roomRatingBar = (RatingBar) v.findViewById(R.id.discover_room_review_rating);
            roomRatingCount = (TextView) v.findViewById(R.id.discover_room_review_count);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public DiscoverRecyclerAdapter(VillimRoom[] dataset) {
        roomList = dataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DiscoverRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.discover_recycler_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) v.getContext();
                Intent intent = new Intent(mainActivity, RoomDetailActivity.class);
                Bundle args = new Bundle();
                args.putString(mainActivity.getString(R.string.key_roomid), "0");
                intent.putExtras(args);
                mainActivity.startActivity(intent);
            }
        });
        populateView(holder, position);
    }

    // Make this async.
    private void populateView(ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        VillimRoom currItem = roomList[position];
        /* Room Title */
        holder.roomTitle.setText(currItem.roomTitle);
        /* Room Rate */
        int price = currItem.roomPrice;
        String priceText = String.format(context.getString(R.string.room_price_value, price));
        holder.roomPriceValue.setText(priceText);
        /* Room Rating Value */
        float rating = getAverageRating(currItem.reviews);
        holder.roomRatingBar.setRating(rating);
        /* Room Rating Count */
        int count = currItem.reviews.length;
        String countText = String.format(context.getString(R.string.room_review_count_text), count);
        holder.roomRatingCount.setText(countText);
        /* Room Tumbnail */
        Glide.with(context)
                .load(R.drawable.prugio_thumbnail)
                .into(holder.roomThumbnail);
    }

    private float getAverageRating(VillimReview[] reviews) {
        float total = 0;
        for (int i = 0; i < reviews.length; i++){
            total += reviews[i].rating;
        }
        return total/reviews.length;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return roomList.length;
    }
}