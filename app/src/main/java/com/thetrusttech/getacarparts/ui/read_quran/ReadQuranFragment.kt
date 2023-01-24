package com.thetrusttech.getacarparts.ui.read_quran

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresPermission.Read
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.jsibbold.zoomage.ZoomageView
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentReadQuranBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment
import com.thetrusttech.getacarparts.ui.read_quran.model.ReadQuranViewModel
import com.thetrusttech.getacarparts.utils.Constants

class ReadQuranFragment : BaseFragment() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val readQuranViewModel: ReadQuranViewModel by viewModels { getViewModelFactory!! }

    private lateinit var binding: FragmentReadQuranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReadQuranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        getQuran()
        loadPdf()
        Glide.with(requireContext())
            .load(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.image_003,
                    requireActivity().theme
                )
            )
            .centerCrop()
            .into(binding.imageView)


        val images = intArrayOf(
            R.drawable.image_003,
            R.drawable.image_004,
            R.drawable.image_005,
            R.drawable.image_006,
            R.drawable.image_007,
            R.drawable.image_008,
            R.drawable.image_009,
            R.drawable.image_010,
            R.drawable.image_011,
            R.drawable.image_012,
            R.drawable.image_013,
            R.drawable.image_014,
            R.drawable.image_015,
            R.drawable.image_016,
            R.drawable.image_017,
            R.drawable.image_018,
            R.drawable.image_019,
            R.drawable.image_020,
            R.drawable.image_021,
            R.drawable.image_022,
            R.drawable.image_023,
            R.drawable.image_024,
            R.drawable.image_025,
            R.drawable.image_026,
            R.drawable.image_027,
            R.drawable.image_028,
            R.drawable.image_029,
            R.drawable.image_030,
            R.drawable.image_031,
            R.drawable.image_032,
            R.drawable.image_033,
            R.drawable.image_034,
            R.drawable.image_035,
            R.drawable.image_036,
            R.drawable.image_037,
            R.drawable.image_038,
            R.drawable.image_039,
            R.drawable.image_040,
            R.drawable.image_041,
            R.drawable.image_042,
            R.drawable.image_043,
            R.drawable.image_044,
            R.drawable.image_045,
            R.drawable.image_046,
            R.drawable.image_047,
            R.drawable.image_048,
            R.drawable.image_049,
            R.drawable.image_050
        )
        viewPagerAdapter = ViewPagerAdapter(requireContext(), images)
        binding.viewPagerMain.adapter = viewPagerAdapter
        binding.viewPagerMain.rotationY = 180F
    }

    private fun setupToolbar() {
        binding.appBar.materialToolbar.title = "Quran"
    }

    private fun getQuran() {
        readQuranViewModel.getQuranData().observe(viewLifecycleOwner) {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d(HomeFragment.TAG, "onViewCreated: ")
                    Log.d("onViewCreated:ApiSuccess", it.body.toString())
                    val response = it.body
                    //carModelAdapter.setItem(response)
                }
                is ApiErrorResponse -> {
                    Log.d("onViewCreated:ApiError", it.errorMessage + " ----" + it.errorCode)
                }
            }
        }
    }

    private fun loadPdf() {
        val webview = binding.webView
        webview.settings.javaScriptEnabled = true
        webview.settings.setSupportZoom(true)

        val pdfViewerURL = "https://drive.google.com/viewerng/viewer?embedded=true&url=";
        val pdfURL = "https://www.quran-pdf.com/arabic-quran2.pdf";
        var url = pdfViewerURL + pdfURL
        webview.loadUrl(url)
    }
}
