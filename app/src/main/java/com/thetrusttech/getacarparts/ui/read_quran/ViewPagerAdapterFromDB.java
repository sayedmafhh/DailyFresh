package com.thetrusttech.getacarparts.ui.read_quran;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.thetrusttech.getacarparts.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ViewPagerAdapterFromDB extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    List<Bitmap> images;

    // Layout Inflater
    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPagerAdapterFromDB(Context context) {
        this.context = context;
        images = new ArrayList<>();
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml 
        View itemView = mLayoutInflater.inflate(R.layout.layout_image_slider_quran, container, false);

        // referencing the image view from the item.xml file 
        ImageView imageView = (ImageView) itemView.findViewById(R.id.my_featured_image);

        // setting the image in the imageView
        Glide.with(context)
                .load(images.get(position))
                .centerCrop()
                .into(imageView);

        imageView.setRotationY(180);

        // Adding the View
        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public List<Bitmap> getImages() {
        return images;
    }
}