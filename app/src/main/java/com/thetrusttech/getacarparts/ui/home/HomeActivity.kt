package com.thetrusttech.getacarparts.ui.home

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ActivityHomeBinding
import com.thetrusttech.getacarparts.ui.home.ui.home.HomeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        loadFragment(HomeFragment.newInstance(), R.id.navigation_home.toString())

        navView.setOnItemSelectedListener {
            val tag = it.itemId.toString()
            when (it.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment.newInstance(), tag)
                }
                /*R.id.secondID-> {
                    // Write your code here
                }*/
            }
            return@setOnItemSelectedListener true
        }

    }

    private fun loadFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

}