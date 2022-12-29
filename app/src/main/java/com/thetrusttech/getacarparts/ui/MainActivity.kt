package com.thetrusttech.getacarparts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    var surahList: MutableList<String> = mutableListOf<String>()
    var itemList: MutableList<Surah> = mutableListOf<Surah>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainer) as NavHostFragment
        navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        setupWithNavController(bottomNavigationView , navController)
        setRecyclerview()
    }

    private fun setRecyclerview() {
        val layoutManager = LinearLayoutManager(this)

        // Recycler view initialize
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)



        itemList.add(Surah("Mazda", "https://com.mazdacdn.com/common/en/assets/innovation/models/img/2021/mazda2.ts.2107090049060000.jpg", "mazda ia a brand of avehicle") )
        itemList.add(Surah("BMW", "https://www.bmw.in/content/dam/bmw/marketIN/bmw_in/all-models/m-series/M8-competition/m8-competition-coupe-header.jpg/jcr:content/renditions/cq5dam.resized.img.1680.large.time1664366514873.jpg", "mazda ia a brand of avehicle") )
        itemList.add(Surah("MERCEDES", "https://www.mercedes-benz.com/en/innovation/concept-cars/revelation-of-luxury-vision-mercedes-maybach-6-cabriolet/_jcr_content/root/slider_0/sliderchilditems/slideritem_0/image/MQ7-0-image-20190114124256/00-mercedes-benz-vehicles-vision-mercedes-maybach-6-cabriolet-3400x1440.jpeg", "mazda ia a brand of avehicle") )
        itemList.add(Surah("HONDA", "https://pkrevenue.com/wp-content/uploads/2022/11/Honda-Accord-2023.jpg", "mazda ia a brand of avehicle") )



        // fetching data from string resource
        val surahNames = resources.getStringArray(R.array.surah)

        // set array into a list
        surahList.addAll(surahNames)

        //initializing adapter and adding data into adapter
        adapter = MyAdapter(itemList, this)

        recyclerView.adapter = adapter
    }
}