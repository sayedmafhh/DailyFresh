package com.thetrusttech.getacarparts.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ActivityHomeBinding
import com.thetrusttech.getacarparts.utils.changeStatusBarColor

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        var color = ContextCompat.getColor(
            applicationContext,
            R.color.blue_masjid
        )
        changeStatusBarColor(
            color, false
        )

        binding.bottomNavigationView.setBackgroundColor(color)
    }
}