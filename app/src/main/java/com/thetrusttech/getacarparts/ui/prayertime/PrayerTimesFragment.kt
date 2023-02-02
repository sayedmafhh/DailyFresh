package com.thetrusttech.getacarparts.ui.prayertime

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentPrayerTimesBinding


class PrayerTimesFragment : BaseFragment() {

    private lateinit var prayerTimeAdapter: PrayerTimeAdapter
    private lateinit var binding: FragmentPrayerTimesBinding
    private val prayerTimeViewModel: PrayerTimeViewModel by viewModels { getViewModelFactory!! }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerview()
        loadPrayerTime()
    }

    private fun setRecyclerview() {

        prayerTimeAdapter = PrayerTimeAdapter()

        binding.rvPrayerTime.apply {
            adapter = prayerTimeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        val list = mutableListOf<PrayerTime>()

        list.add(PrayerTime(0, "Fajr", "6:20 am"))
        list.add(PrayerTime(0, "Fajr", "6:20 am"))
        list.add(PrayerTime(0, "Fajr", "6:20 am"))
        list.add(PrayerTime(0, "Fajr", "6:20 am"))
        list.add(PrayerTime(0, "Fajr", "6:20 am"))

        prayerTimeAdapter.setItem(list)


    }
    private fun loadPrayerTime() {

        val geocoder = Geocoder(requireContext())
        val addressList: kotlin.collections.List<Address>?
        try {
            //addressList=geocoder.getFromLocationName(searchEditText?.getText().toString(),5)
            addressList = geocoder.getFromLocationName("karachi", 5)
            addressList
            /* if (addressList!=null)
             {
                 val doubleLat=addressList[0].latitude
                 val doubleLong=addressList[0].latitude
                 val queue=Volley.newRequestQueue(this)
                 val url = "https://api.aladhan.com/v1/calendar?latitude=" + doubleLat + "&Longitude=" + doubleLong + ""
                 val JsonObjectRequest=JsonObjectRequest(Request.Method.GET,Url,null)
             }*/
        } catch (e: Exception) { // handler }

        }
    }
}