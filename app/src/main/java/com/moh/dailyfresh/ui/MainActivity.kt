package com.moh.dailyfresh.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.items.activity.RecipeItemDetailsActivity
import com.moh.dailyfresh.ui.items.activity.RecipeItemsActivity
import com.moh.dailyfresh.ui.viewmodels.MainViewModel
import com.moh.dailyfresh.utils.Constants.Companion.EXTRA_TITLE

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityMainBinding
    var title: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getRecipeCategory().observe(this, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("MainApiSuccessResponse", it.body.toString())
                    val response = it.body
                    title = response.categories!![0].title.toString()
                }
                is ApiErrorResponse -> {
                    Log.d("Main.ApiErrorResponse", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })
        binding.btnNewActivity.setOnClickListener {
            startNewActivity(title)
        }
    }

    private fun startNewActivity(title: String) {
        val intent = Intent(this, RecipeItemDetailsActivity::class.java).apply {
            putExtra(EXTRA_TITLE, title)
        }
        startActivity(intent)
    }
}