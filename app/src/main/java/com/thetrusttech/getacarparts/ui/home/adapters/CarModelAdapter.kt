package com.thetrusttech.getacarparts.ui.home.adapters

import android.content.Context
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.LayoutAdapterCarMakeBinding
import com.thetrusttech.getacarparts.databinding.LayoutAdapterCarModelBinding
import com.thetrusttech.getacarparts.models.responses.make.CarMake
import com.thetrusttech.getacarparts.models.responses.model.CarModel
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import com.thetrusttech.getacarparts.utils.Constants

class CarModelAdapter(private val context: Context) : RecyclerAdapter<CarModel, LayoutAdapterCarModelBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.layout_adapter_car_model
    }

    override fun onBindData(
        listItem: CarModel,
        dataBinding: LayoutAdapterCarModelBinding,
        position: Int
    ) {
        Glide.with(context)
            .load(Constants.BASE_URL + listItem.icon)
            .centerCrop()
            .into(dataBinding.ivCarModel)
    }

    override fun onItemClick(listItem: CarModel, position: Int) {
    }

    override fun setCurrentPage(pageName: String) {
    }

}