package com.thetrusttech.getacarparts.ui.quran

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.FragmentQuranBinding
import com.thetrusttech.getacarparts.ui.QuranAdapter
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG


class QuranFragment : Fragment() {

    private lateinit var quranAdapter: QuranAdapter
    var surahList: MutableList<String> = mutableListOf()
    var itemList: MutableList<Surah> = mutableListOf()
    var parahList: MutableList<Surah> = mutableListOf()

    private lateinit var binding: FragmentQuranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerview()
        setTabLayout()
    }

    private fun setTabLayout() {
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("Surah")
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText("Juz")
        )

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d(TAG, "onTabSelected: " + tab!!.id.toString())
                when(tab.position) {
                    0 -> loadSurah()
                    1 -> loadJuz()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun loadJuz() {
        quranAdapter.setItem(itemList)
    }

    private fun loadSurah() {
        val parahNames = resources.getStringArray(R.array.parah_list)

        var count = 1
        parahNames.forEach {
            parahList.add(Surah(count++, it, "سُوْرَۃُ الفَاتِحَة", "", "The Cow"))
        }

        quranAdapter.setItem(parahList)
    }

    private fun setRecyclerview() {
        val layoutManager = LinearLayoutManager(context)

        // fetching data from string resource
        val surahNames = resources.getStringArray(R.array.surah_names)

        // set array into a list
        surahList.addAll(surahNames)
        var countt = 1

        surahList.forEach {
            itemList.add(Surah(countt++, it, "سُوْرَۃُ الفَاتِحَة", "", "The Cow"))

        }

        // Recycler view initialize
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.setHasFixedSize(true)

        //initializing adapter and adding data into adapter
        quranAdapter = QuranAdapter(requireContext())
        binding.recyclerView.adapter = quranAdapter

        quranAdapter.setItem(itemList)
    }
}