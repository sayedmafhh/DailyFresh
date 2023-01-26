package com.thetrusttech.getacarparts.ui

import android.content.Context
import android.view.View
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ListItemBinding
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import com.thetrusttech.getacarparts.ui.quran.SurahList.ListItem

class QuranAdapter(val context: Context, val clickListener: IItemClickListener) : RecyclerAdapter<ListItem, ListItemBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.list_item
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindData(listItem: ListItem, dataBinding: ListItemBinding, position: Int) {
        if (listItem.viewType == 0) {
            dataBinding.viewCard.root.visibility = View.VISIBLE
            dataBinding.viewCardJuz.root.visibility = View.GONE

            dataBinding.viewCard.txtSurahDesc.text = listItem.description
            dataBinding.viewCard.txtSurahTotalVerses.text = listItem.total_verses
            dataBinding.viewCard.txtSurahName.text = listItem.name
            dataBinding.viewCard.txtSurahNameArabic.text = listItem.name_ar
            dataBinding.viewCard.txtSurahNumber.text = listItem.id.toString()
        } else if (listItem.viewType == 1){
            dataBinding.viewCardJuz.root.visibility = View.VISIBLE
            dataBinding.viewCard.root.visibility = View.GONE

            dataBinding.viewCardJuz.txtSurahName.text = listItem.name
            dataBinding.viewCardJuz.txtSurahNameArabic.text = listItem.name_ar
            dataBinding.viewCardJuz.txtSurahNumber.text = listItem.id.toString()

        }


    }

    override fun onItemClick(listItem: ListItem, position: Int) {
        clickListener.itemClickListener(listItem.page_number)
    }

    override fun setCurrentPage(pageName: String) {

    }

    interface IItemClickListener {
        fun itemClickListener(page_position: Int)
    }

}