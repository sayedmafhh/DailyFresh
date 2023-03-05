package com.thetrusttech.getacarparts.ui.prayertime

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentPrayerTimesBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment.Companion.TAG
import com.thetrusttech.getacarparts.ui.prayertime.model.PrayerTimeResponse
import com.thetrusttech.getacarparts.utils.changeStatusBarColor
import com.thetrusttech.getacarparts.utils.convertTo12Hours
import java.text.SimpleDateFormat
import java.util.*


class PrayerTimesFragment : BaseFragment() {

    private lateinit var prayerTimeAdapter: PrayerTimeAdapter
    private lateinit var binding: FragmentPrayerTimesBinding
    private val prayerTimeViewModel: PrayerTimeViewModel by viewModels { getViewModelFactory!! }
    private var location = ""
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
            } else {
                // Explain to the user that the feature is unavailable because the
                // feature requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
            }
        }

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                when (it.key) {
                    Manifest.permission.ACCESS_COARSE_LOCATION -> {
                        if (it.value) {
                            requestCurrentLocation()
                        } else
                            Log.i(TAG, "ACCESS_COARSE_LOCATION permission denied")
                    }
                }
            }
        }


    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private var cancellationTokenSource = CancellationTokenSource()


    private fun requestLocationPermission() {
        if (!isAccessFineLocationPermissionGranted()) {
            requestMultiplePermissions.launch(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION))
        }
    }

    private fun isAccessFineLocationPermissionGranted(): Boolean {
        return checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPrayerTimesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBar()
        setRecyclerview()
        requestCurrentLocation()

    }

    private fun setStatusBar() {
        requireActivity().changeStatusBarColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.blue_masjid
            ), false
        )
    }

    private fun setRecyclerview() {

        prayerTimeAdapter = PrayerTimeAdapter()

        binding.rvPrayerTime.apply {
            adapter = prayerTimeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun loadPrayerTime(lat: Double, lon: Double) {
        getLocation(lat, lon)
        val url =
            "https://api.aladhan.com/v1/calendar?latitude=$lat&longitude=$lon"
        prayerTimeViewModel.getPrayerTime(url).observe(viewLifecycleOwner) {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d(TAG, "loadPrayerTime: " + it.body.toString())
                    val response = it.body
                    updatePrayerTime(response)
                }
                is ApiErrorResponse -> {
                    Log.d("Main.ApiErrorResponse", it.errorMessage + " ----" + it.errorCode)

                }
            }
        }


    }

    private fun updatePrayerTime(response: PrayerTimeResponse) {

        var data = response.data[0]

        binding.hijri.text = data.date.hijri.day + " " + data.date.hijri.month.en + " " + data.date.hijri.year
        binding.location.text = prayerTimeViewModel.getLocation()

        val list = mutableListOf<PrayerTime>()

        val fajr = data.timings.Fajr.filter { it.isLetter() }

        System.currentTimeMillis()
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val time1: String = "05:00:00"
        val time2: String = "18:00:00"

        val difference: Long =
            simpleDateFormat.parse(time2).getTime() - simpleDateFormat.parse(time1).getTime()
        val minutes = difference / 1000 / 60

        //println("Difference in minutes: " + code.toLong() + minutes)

        val dhuhr = data.timings.Dhuhr
        val asr = data.timings.Asr
        val maghrib = data.timings.Maghrib
        val isha = data.timings.Isha


        val time: Double = 0.0
        /*if (time > fajr && time < dhuhr)
        binding.tvPrayerTimeRemaining.text*/

        list.add(PrayerTime(0, "Fajr", convertTo12Hours(fajr)))
        list.add(PrayerTime(1, "Dhuhr", convertTo12Hours(dhuhr)))
        list.add(PrayerTime(2, "Asr", convertTo12Hours(asr)))
        list.add(PrayerTime(3, "Maghrib", convertTo12Hours(maghrib)))
        list.add(PrayerTime(4, "Isha", convertTo12Hours(isha)))

        prayerTimeAdapter.setItem(list)
    }

    private fun requestCurrentLocation() {

        if(prayerTimeViewModel.getLatitude() != 0f
            && prayerTimeViewModel.getLatitude() != 0f)
            loadPrayerTime(prayerTimeViewModel.getLatitude().toDouble()
                , prayerTimeViewModel.getLongitude().toDouble())


        // Check Fine permission
        if ((ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        ) {

            // Main code
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                val result = if (task.isSuccessful) {
                    if (task.result != null) {
                        val result: Location = task.result
                        "Location (success): ${result.latitude}, ${result.longitude}"
                        prayerTimeViewModel.storeLatitude(result.latitude)
                        prayerTimeViewModel.storeLongitude(result.longitude)
                        loadPrayerTime(result.latitude, result.longitude)
                    } else {
                        loadPrayerTime(prayerTimeViewModel.getLatitude().toDouble()
                            , prayerTimeViewModel.getLongitude().toDouble())
                    }
                } else {
                    val exception = task.exception
                    "Location (failure): $exception"
                }

                Log.d(TAG, "getCurrentLocation() result: $result")
            }
        } else {
            requestPermission()
        }
    }

    private fun requestPermission() {
        requestLocationPermission()
    }

    fun getLocation(lat: Double,lon: Double) {
        val geocoder = Geocoder(requireContext())
        val addressList: kotlin.collections.List<Address>?
        try {
            addressList = geocoder.getFromLocation(lat, lon, 1)
            //addressList
            /* if (addressList!=null)
             {
                 val doubleLat=addressList[0].latitude
                 val doubleLong=addressList[0].latitude
                 val queue=Volley.newRequestQueue(this)
                 val url = "
                 =" + doubleLat + "&Longitude=" + doubleLong + ""
                 val JsonObjectRequest=JsonObjectRequest(Request.Method.GET,Url,null)
             }*/

            if (addressList != null) {
                val countryCode = addressList[0].countryCode
                val cityName = addressList[0].locality
                prayerTimeViewModel.storeLocation("$cityName, $countryCode")
                binding.location.text = "$cityName, $countryCode"
            }
        } catch (e: Exception) { // handler }

        }
    }
}