package com.thetrusttech.getacarparts.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.getSystemService
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.utils.Constants

class CarouselAdapter(private val context: Context, private val itemList: List<String>) : PagerAdapter() {
    lateinit var layoutInflater:LayoutInflater

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.layout_image_slider_quran,container,false)
        val img = view.findViewById<ImageView>(R.id.my_featured_image)

        Glide.with(context)
            .load(itemList[position])
            .centerCrop()
            .into(img)

        container.addView(view,0)

        return view
    }

    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view == o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}