package com.thetrusttech.getacarparts.ui.home.adapters

import android.content.Context
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.LayoutAdapterCarMakeBinding
import com.thetrusttech.getacarparts.models.responses.make.CarMake
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import com.thetrusttech.getacarparts.utils.Constants.Companion.BASE_URL

class CarMakeAdapter(private val context: Context) : RecyclerAdapter<CarMake, LayoutAdapterCarMakeBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.layout_adapter_car_make
    }

    override fun onBindData(
        listItem: CarMake,
        dataBinding: LayoutAdapterCarMakeBinding,
        position: Int
    ) {
        Glide.with(context)
            .load(BASE_URL + listItem.icon)
            .centerCrop()
            .into(dataBinding.ivCarMake)
    }

    override fun onItemClick(listItem: CarMake, position: Int) {
    }

    override fun setCurrentPage(pageName: String) {
    }
}