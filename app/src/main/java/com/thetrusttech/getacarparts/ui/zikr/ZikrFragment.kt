package com.thetrusttech.getacarparts.ui.zikr

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.asgl.sdk.common.CommonUitlities
import com.google.android.material.tabs.TabLayout
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentQuranBinding
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG
import com.thetrusttech.getacarparts.ui.quran.SurahList


class ZikrFragment : BaseFragment() {

    private lateinit var zikrAdapter: ZikrAdapter

    private lateinit var binding: FragmentQuranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    private fun loadJuz() {var parahList1 =
        CommonUitlities.getObjectFromResponse( "", SurahList::class.java, "juz_list.json") as SurahList
        parahList1.juz.forEach {
            it.viewType = 1
        }
        zikrAdapter.setItem(parahList1.juz)
    }

    private fun loadSurah() {
        var parahList1 =
            CommonUitlities.getObjectFromResponse( "", SurahList::class.java, "surah_list.json") as SurahList
        parahList1.surah.forEach {
            it.viewType = 0
        }
        zikrAdapter.setItem(parahList1.surah)
    }

    private fun setRecyclerview() {
        zikrAdapter = ZikrAdapter(requireContext())
        // Recycler view initialize
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = zikrAdapter
            setHasFixedSize(true)
        }
        loadSurah()
    }

}