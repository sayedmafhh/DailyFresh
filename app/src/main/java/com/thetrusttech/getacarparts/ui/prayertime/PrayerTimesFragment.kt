package com.thetrusttech.getacarparts.ui.prayertime

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentPrayerTimesBinding
import com.thetrusttech.getacarparts.databinding.FragmentQuranBinding


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

}