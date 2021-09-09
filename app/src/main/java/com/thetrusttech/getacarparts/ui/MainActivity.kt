package com.thetrusttech.getacarparts.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ActivityMainBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.base.BaseActivity
import com.thetrusttech.getacarparts.ui.items.activity.RecipeItemDetailsActivity
import com.thetrusttech.getacarparts.ui.viewmodels.MainViewModel
import com.thetrusttech.getacarparts.utils.Constants.Companion.EXTRA_TITLE

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