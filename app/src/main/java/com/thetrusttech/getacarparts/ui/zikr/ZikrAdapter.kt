package com.thetrusttech.getacarparts.ui.zikr

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import com.thetrusttech.getacarparts.databinding.LayoutZikrListItemBinding
import com.thetrusttech.getacarparts.ui.quran.SurahList.ListItem

class ZikrAdapter(val context: Context, val listener: IZikrItemClickListener) : RecyclerAdapter<ListItem, LayoutZikrListItemBinding>() {

    override fun getLayoutResId() = R.layout.layout_zikr_list_item

    override fun onBindData(
        listItem: ListItem,
        dataBinding: LayoutZikrListItemBinding,
        position: Int
    ) {
        dataBinding.txtSurahName.text = listItem.name

        Glide.with(context)
            .load("https://m.media-amazon.com/images/I/919B5KYZfwL.jpg")
            .centerCrop()
            .into(dataBinding.ivZikr)
    }

    override fun onItemClick(listItem: ListItem, position: Int) {
        listener.onZikrItemClickListener(listItem.page_number)
    }

    override fun setCurrentPage(pageName: String) {}

    interface IZikrItemClickListener {
        fun onZikrItemClickListener(page_position: Int)
    }

}