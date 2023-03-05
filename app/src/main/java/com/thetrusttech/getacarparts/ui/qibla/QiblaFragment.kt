package com.thetrusttech.getacarparts.ui.qibla

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseFragment
import com.thetrusttech.getacarparts.databinding.FragmentQiblaBinding
import kotlin.math.roundToInt


class QiblaFragment : BaseFragment(), SensorEventListener {

    private lateinit var manager: SensorManager
    private lateinit var sensor: Sensor
    private lateinit var binding: FragmentQiblaBinding
    private var currentDegree = 0f
    private var angle = 55.27f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQiblaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.angle.text = angle.toString()
        binding.qiblaIndicator.rotation = angle
        getLocation()
        manager = requireActivity().getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        manager.registerListener(
            this,
            manager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val degree = event!!.values[0].roundToInt().toFloat()
        val animation = RotateAnimation(
            currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 120
        animation.fillAfter = true
        binding.layoutDial.startAnimation(animation)
        currentDegree = -degree
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    fun getLocation(lat: Double = 44.285690,lon: Double = -78.366510) {
        val geocoder = Geocoder(requireContext())
        val addressList: kotlin.collections.List<Address>?
        try {
            addressList = geocoder.getFromLocation(lat, lon, 1)
            if (addressList != null) {
                val countryCode = addressList[0].countryCode
                val cityName = addressList[0].locality
                binding.yourLocation.text = "$cityName, $countryCode"
            }
        } catch (e: Exception) { // handler }

        }
    }
}