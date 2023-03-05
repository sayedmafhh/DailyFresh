package com.thetrusttech.getacarparts.ui.read_quran

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentReadQuranBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiEmptyResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG
import com.thetrusttech.getacarparts.ui.read_quran.model.ReadQuranViewModel
import com.thetrusttech.getacarparts.utils.Cache
import com.thetrusttech.getacarparts.utils.MemoryStorageManager
import com.thetrusttech.getacarparts.utils.PDFManager
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class ReadQuranFragment : BaseFragment() {

    //private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewPagerAdapterFromDB: ViewPagerAdapterFromDB
    private val readQuranViewModel: ReadQuranViewModel by viewModels { getViewModelFactory!! }

    private lateinit var binding: FragmentReadQuranBinding

    private val navArgs by navArgs<ReadQuranFragmentArgs>()

    private lateinit var memoryStorageManager: MemoryStorageManager


    var myExternalFile: File? = null
    var outputStream: FileOutputStream? = null

    private var latestTmpUri: Uri? = null

    private val takeImageResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                latestTmpUri?.let { uri ->
                    Log.d(HomeFragment.TAG, "ReadQuranFragment: $uri ")
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentReadQuranBinding.inflate(inflater, container, false)
        memoryStorageManager = MemoryStorageManager(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        /*Glide.with(requireContext())
            .load(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.image_003,
                    requireActivity().theme
                )
            )
            .centerCrop()
            .into(binding.imageView)*/

        binding.openCam.setOnClickListener {
            //takeImage()
            val files = MemoryStorageManager(requireContext()).loadFilesFromFromFileDIR()
            files.forEach {
                Log.d(TAG, "onViewCreated: ${it.path}")
            }
        }
        binding.getImage.setOnClickListener {
            //MemoryStorageManager(requireContext()).displayImage(binding.imageView)
            getPDFFromServer()
        }


        //memoryStorageManager.saveImageIntoGallery(binding.imageView, requireActivity())

        //myExternalFile = File(requireContext().getExternalFilesDir(filepath), filename)
        //getQuran()
        //loadPdf()


        //viewPagerAdapter = ViewPagerAdapter(requireContext(), images)
        viewPagerAdapterFromDB = ViewPagerAdapterFromDB(requireContext())
        binding.viewPagerMain.rotationY = 180F
        binding.viewPagerMain.currentItem = navArgs.pagePosition
        binding.viewPagerMain.adapter = viewPagerAdapterFromDB
    }

    private fun takeImage() {
        lifecycleScope.launchWhenStarted {
            MemoryStorageManager(requireContext()).getTmpFileUri().let { uri ->
                latestTmpUri = uri
                takeImageResult.launch(uri)
            }
        }
    }

    fun getAlbumStorageDir(context: Context, albumName: String?): File? {
        // Get the directory for the app's private pictures directory.
        val file = File(
            context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES
            ), albumName
        )
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created")
        }
        return file
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

    private fun getPDFFromServer() {
        val url = "https://data2.dawateislami.net/Data/Books/Download/en/pdf/2023/3154-1.pdf?fn=the-blessing-of-rajab-ul-murajjab"
        readQuranViewModel.getPDF(url).observe(viewLifecycleOwner) {
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
                    updateViewPager(list)
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

    private fun updateViewPager(list: List<Bitmap>) {
        viewPagerAdapterFromDB.setImages(list)
        viewPagerAdapterFromDB.notifyDataSetChanged()
    }

    private fun InputStream.saveToFile(file: String) = use { input ->
        File(file).outputStream().use { output ->
            input.copyTo(output)
        }
    }

    private fun createFile(context: Context, name: String): File? {
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.path
        var file = File("$storageDir/$name.pdf")
        return storageDir?.let { file }
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
