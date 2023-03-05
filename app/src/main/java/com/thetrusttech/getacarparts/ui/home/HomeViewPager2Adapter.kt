package com.thetrusttech.getacarparts.ui.home

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import com.thetrusttech.getacarparts.databinding.LayoutImageSliderQuranBinding

class HomeViewPager2Adapter(var context: Context): RecyclerAdapter<Bitmap, LayoutImageSliderQuranBinding>() {
    override fun getLayoutResId() = R.layout.layout_image_slider_quran

    override fun setCurrentPage(pageName: String) {}

    override fun onItemClick(listItem: Bitmap, position: Int) {}

    override fun onBindData(
        listItem: Bitmap,
        dataBinding: LayoutImageSliderQuranBinding,
        position: Int,
    ) {

        // setting the image in the imageView
        Glide.with(context)
            .load(listItem)
            .centerCrop()
            .into(dataBinding.myFeaturedImage)
    }
}