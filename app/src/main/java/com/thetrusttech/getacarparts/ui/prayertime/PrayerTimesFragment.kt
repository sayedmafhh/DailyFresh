package com.thetrusttech.getacarparts.ui.prayertime

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ListItemBinding
import com.thetrusttech.getacarparts.base.RecyclerAdapter


class PrayerTimesFragment : Fragment() {

    //private lateinit var prayerAdapter: PrayerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prayer_times, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*prayerAdapter = PrayerAdapter()

        var rv = view.findViewById<RecyclerView>(R.id.rv_prayer_time)
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = prayerAdapter
        }

        val arr = resources.getStringArray(R.array.surah_names).toList()
        prayerAdapter.setItem(arr)*/

    }

    /*class PrayerAdapter : RecyclerAdapter<String, ListItemBinding>() {
        override fun getLayoutResId(): Int {
            return R.layout.list_item
        }

        override fun onBindData(listItem: String, dataBinding: ListItemBinding, position: Int) {
            //dataBinding.surah.text = listItem
        }

        override fun onItemClick(listItem: String, position: Int) {
        }

        override fun setCurrentPage(pageName: String) {
        }

    }*/
}