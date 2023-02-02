package com.thetrusttech.getacarparts.ui.prayertime

import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.RecyclerAdapter
import com.thetrusttech.getacarparts.databinding.LayoutPrayerTimeBinding

class PrayerTimeAdapter: RecyclerAdapter<PrayerTime,LayoutPrayerTimeBinding>() {
    override fun getLayoutResId() = R.layout.layout_prayer_time

    override fun onBindData(
        listItem: PrayerTime,
        dataBinding: LayoutPrayerTimeBinding,
        position: Int,
    ) {
        dataBinding.tvPrayerTimeLabel.text = listItem.prayerName
        dataBinding.tvPrayerTime.text = listItem.prayerTime
    }

    override fun onItemClick(listItem: PrayerTime, position: Int) {}

    override fun setCurrentPage(pageName: String) {}
}