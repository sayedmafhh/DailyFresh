package com.thetrusttech.getacarparts.ui

import android.content.Context
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ListItemBinding
import com.thetrusttech.getacarparts.ui.base.RecyclerAdapter
import com.thetrusttech.getacarparts.ui.quran.Surah

class QuranAdapter(val context: Context) : RecyclerAdapter<Surah, ListItemBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.list_item
    }

    override fun onBindData(listItem: Surah, dataBinding: ListItemBinding, position: Int) {
        dataBinding.viewCard.txtSurahName.text = listItem.name
        dataBinding.viewCard.txtSurahDesc.text = listItem.smallDesc
        dataBinding.viewCard.txtSurahNameArabic.text = listItem.arabicName
        dataBinding.viewCard.txtSurahNumber.text = listItem.id.toString()
    }

    override fun onItemClick(listItem: Surah, position: Int) {

    }

    override fun setCurrentPage(pageName: String) {

    }

}