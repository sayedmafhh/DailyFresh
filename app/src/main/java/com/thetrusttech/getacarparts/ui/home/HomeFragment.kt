package com.thetrusttech.getacarparts.ui.home

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentHomeBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiEmptyResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment
import com.thetrusttech.getacarparts.utils.PDFManager
import com.thetrusttech.getacarparts.utils.changeStatusBarColor
import java.io.File

class HomeFragment : BaseFragment(){

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewPagerAdapter: HomeViewPager2Adapter
    private val homeViewModel: HomeFragViewModel by viewModels { getViewModelFactory!! }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerAdapter = HomeViewPager2Adapter(requireContext())

        var color = ContextCompat.getColor(
            requireContext(),
            R.color.blue_masjid
        )

        requireActivity().changeStatusBarColor(color, false)
        binding.mainLayout.setBackgroundColor(color)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.setPreviewBothSide(R.dimen._20sdp,R.dimen._35sdp)
        getPDFFromServer()
    }

    class HorizontalMarginItemDecoration(context: Context, @DimenRes horizontalMarginInDp: Int) :
        RecyclerView.ItemDecoration() {
        private val horizontalMarginInPx: Int =
            context.resources.getDimension(horizontalMarginInDp).toInt()

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
        ) {
            outRect.right = horizontalMarginInPx
            outRect.left = horizontalMarginInPx
        }
    }

    fun ViewPager2.setPreviewBothSide(@DimenRes nextItemVisibleSize: Int, @DimenRes currentItemHorizontalMargin: Int) {
        this.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(nextItemVisibleSize)
        val currentItemHorizontalMarginPx = resources.getDimension(currentItemHorizontalMargin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        }
        this.setPageTransformer(pageTransformer)

        val itemDecoration = HorizontalMarginItemDecoration(
            context,
            currentItemHorizontalMargin
        )
        this.addItemDecoration(itemDecoration)
    }

    private fun getPDFFromServer() {
        //val url = "https://data2.dawateislami.net/Data/Books/Download/en/pdf/2023/3154-1.pdf?fn=the-blessing-of-rajab-ul-murajjab"
        val url = "https://api8.ilovepdf.com/v1/download/plrwlv18wdk843z8nlxrdbvm7mmkyvl0pAb270ky2q32575h78mgg7v58qgf2ths306ryvyzAwngptkqglfp52hq38kdk6mt30hdbmpj3d8yp12wdm62hgxzAsq0rm4csAlyqxmg6tjA3fAdvpfq5b3wx3kqA06j9frxv5bdkrkfn8z46g41"
        homeViewModel.getPDF(url).observe(viewLifecycleOwner) {
            when (it) {
                is ApiSuccessResponse -> {
                    it.body
                    Log.d(HomeFragment.TAG, "onViewCreated: ")
                    Log.d("onViewCreated:ApiSuccess", it.body.toString())
                    val response = it.body
                    val file = getTmpFileUri()
                    it.body.byteStream().use {input->
                        file.outputStream().use {output->
                            input.copyTo(output)
                        }
                    }
                    val list = PDFManager().convertPDFtoImage(requireContext(), file)
                    viewPagerAdapter.setItem(list)
                }
                is ApiErrorResponse -> {
                    Log.d("onViewCreated:ApiError", it.errorMessage + " ----" + it.errorCode)
                }
                is ApiEmptyResponse -> {
                    Log.d("onViewCreated:ApiError" , "empty message")
                }
            }
        }
    }

    fun getTmpFileUri(): File {

//        val cachePath = File(requireContext().cacheDir, "PDF")
//        cachePath.mkdirs()
//
//        val stream: FileOutputStream =
//            FileOutputStream(cachePath.path + "/tmp_image_file.pdf")
        //val imageFile = File("${requireActivity().filesDir}/PDF/tmp_image_file.pdf")
        val tmpFile = File.createTempFile("tmp_image_file", ".pdf", requireContext().filesDir).apply {
            createNewFile()
            deleteOnExit()
        }
        //stream.close()
        return tmpFile
    }
}
