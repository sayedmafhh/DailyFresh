package com.thetrusttech.getacarparts.ui.qibla

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.thetrusttech.getacarparts.R

class QiblaActivity : AppCompatActivity(), SensorEventListener {
    private var compass: ImageView? = null
    private var current_degree = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qibla)
        compass = findViewById(R.id.dial)
        manager = getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        manager!!.registerListener(
            this,
            manager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION),
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun onPause() {
        super.onPause()
        manager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        val degree = Math.round(event.values[0]).toFloat()
        val animation = RotateAnimation(
            current_degree, -degree, Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 120
        animation.fillAfter = true
        compass!!.startAnimation(animation)
        current_degree = -degree
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}

    companion object {
        private var manager: SensorManager? = null
        private val sensor: Sensor? = null
    }
}