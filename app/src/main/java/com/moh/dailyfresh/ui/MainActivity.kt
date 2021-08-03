package com.moh.dailyfresh.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.viewmodels.MainViewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getRecipe().observe(this, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("MainApiSuccessResponse", it.body.toString())
                }
                is ApiErrorResponse -> {
                    Log.d("Main.ApiErrorResponse", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })
        binding.tvHello.text = "Salam Syed!"
    }
}