package com.thetrusttech.getacarparts.ui.home.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thetrusttech.getacarparts.databinding.FragmentHomeBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.base.BaseFragment
import com.thetrusttech.getacarparts.ui.home.adapters.CarMakeAdapter
import com.thetrusttech.getacarparts.ui.home.adapters.CarModelAdapter
import com.thetrusttech.getacarparts.ui.home.adapters.CarouselAdapter

class HomeFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModels { getViewModelFactory!! }

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var carMakeAdapter: CarMakeAdapter
    private lateinit var carModelAdapter: CarModelAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initRecyclerview()

        return root
    }

    private fun initRecyclerview() {
        val imageList: MutableList<String> = mutableListOf()
        imageList.add("https://di-uploads-pod4.s3.amazonaws.com/titanautomotivegroup/uploads/2015/11/Car-Parts-2.jpg")
        imageList.add("https://allcars.estoreseller.com/estore/a2z/images/banner1.png")
        carMakeAdapter = CarMakeAdapter(requireContext())
        carModelAdapter = CarModelAdapter(requireContext())
        carouselAdapter = CarouselAdapter(requireContext(), imageList)

        binding.rvCarMake.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = carMakeAdapter
        }

        binding.rvCarModel.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = carModelAdapter
        }

        binding.viewTopBannerLayout.myPager.apply {
            adapter = carouselAdapter
        }
        binding.viewTopBannerLayout.
        /*
        binding.rvUserQuotation.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userQuotationAdapter
        }
        binding.rvSellItem.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sellItemAdapter
        }*/

        fetchCarMake()
        fetchCarModel()
        //fetchCarQuotation()
        //fetchSellItems()
    }

    private fun fetchCarMake() {
        homeViewModel.getCarMake().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("HomeFragment.ApiSuccess", it.body.toString())
                    val response = it.body
                    carMakeAdapter.setItem(response)
                }
                is ApiErrorResponse -> {
                    Log.d("HomeFragment.ApiError", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })

    }

    private fun fetchCarModel() {
        homeViewModel.getCarModel().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("CarModel:ApiSuccess", it.body.toString())
                    val response = it.body
                    carModelAdapter.setItem(response)
                }
                is ApiErrorResponse -> {
                    Log.d("CarModel:ApiError", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })

    }

    private fun fetchCarQuotation() {
        homeViewModel.getCarMake().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("HomeFragment.ApiSuccess", it.body.toString())
                    val response = it.body
                    carMakeAdapter.setItem(response)
                }
                is ApiErrorResponse -> {
                    Log.d("HomeFragment.ApiError", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })

    }

    private fun fetchSellItems() {
        homeViewModel.getCarMake().observe(viewLifecycleOwner, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("HomeFragment.ApiSuccess", it.body.toString())
                    val response = it.body
                    carMakeAdapter.setItem(response)
                }
                is ApiErrorResponse -> {
                    Log.d("HomeFragment.ApiError", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}