package com.thetrusttech.getacarparts.ui.quran

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asgl.sdk.common.CommonUitlities
import com.google.android.material.tabs.TabLayout
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentQuranBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.QuranAdapter
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeViewModel


class QuranFragment : BaseFragment(), QuranAdapter.IItemClickListener {

    private lateinit var quranAdapter: QuranAdapter

    private lateinit var binding: FragmentQuranBinding

    private val quranViewModel: QuranViewModel by viewModels { getViewModelFactory!! }

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
        quranAdapter.setItem(parahList1.juz)
    }

    private fun loadSurah() {
        var parahList1 =
            CommonUitlities.getObjectFromResponse( "", SurahList::class.java, "surah_list.json") as SurahList
        parahList1.surah.forEach {
            it.viewType = 0
        }
        quranAdapter.setItem(parahList1.surah)
    }

    private fun setRecyclerview() {
        quranAdapter = QuranAdapter(requireContext(), this)
        // Recycler view initialize
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = quranAdapter
            setHasFixedSize(true)
        }
        loadSurah()
    }

    private fun navigateToReadQuran() {
        R.navigation.mobile_navigation
        val nav = QuranFragmentDirections.actionNavigationQuranFragmentToReadQuranFragment()
        //findNavController().navigate(nav)
        NavHostFragment.findNavController(this).navigate(nav)
    }

    override fun itemClickListener() {
        navigateToReadQuran()
    }
}