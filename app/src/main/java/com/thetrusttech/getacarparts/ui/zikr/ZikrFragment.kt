package com.thetrusttech.getacarparts.ui.zikr

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asgl.sdk.common.CommonUitlities
import com.google.android.material.tabs.TabLayout
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentQuranBinding
import com.thetrusttech.getacarparts.databinding.FragmentZikrBinding
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG
import com.thetrusttech.getacarparts.ui.quran.SurahList


class ZikrFragment : BaseFragment(), ZikrAdapter.IZikrItemClickListener {

    private lateinit var zikrAdapter: ZikrAdapter

    private lateinit var binding: FragmentZikrBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentZikrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerview()
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
        zikrAdapter = ZikrAdapter(requireContext(), this)
        // Recycler view initialize
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
            adapter = zikrAdapter
            setHasFixedSize(true)
        }
        loadSurah()
    }

    override fun onZikrItemClickListener(page_position: Int) {
        Toast.makeText(requireContext(), "Item Clicked on Position : $page_position", Toast.LENGTH_SHORT).show()
    }

}