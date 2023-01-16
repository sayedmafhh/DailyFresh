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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentQuranBinding
import com.thetrusttech.getacarparts.databinding.FragmentReadQuranBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG
import com.thetrusttech.getacarparts.ui.quran.QuranViewModel
import com.thetrusttech.getacarparts.ui.read_quran.model.ReadQuranViewModel

class ReadQuranFragment : BaseFragment(){

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
