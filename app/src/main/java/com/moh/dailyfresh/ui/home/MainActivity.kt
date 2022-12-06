package com.moh.dailyfresh.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.home.adapter.CategoryAdapter
import com.moh.dailyfresh.ui.items.activity.RecipeItemDetailsActivity
import com.moh.dailyfresh.ui.home.viewmodels.MainViewModel
import com.moh.dailyfresh.ui.items.activity.RecipeItemsActivity
import com.moh.dailyfresh.utils.Constants.Companion.EXTRA_TITLE

class MainActivity : BaseActivity(), ICategory {

    private val viewModel: MainViewModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityMainBinding
    var title: String = ""
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getRecipeCategory().observe(this, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    Log.d("MainApiSuccessResponse", it.body.toString())
                    val response = it.body
                    categoryAdapter.setItem(response.categories!!)
                }
                is ApiErrorResponse -> {
                    Log.d("Main.ApiErrorResponse", it.errorMessage + " ----" + it.errorCode)

                }
            }
        })
        binding.btnNewActivity.setOnClickListener {
            startNewActivity(title)
        }
        initList(binding)
    }

    private fun startNewActivity(title: String) {
        val intent = Intent(this, RecipeItemsActivity::class.java).apply {
            putExtra(EXTRA_TITLE, title)
        }
        startActivity(intent)
    }

    private fun initList(binding: ActivityMainBinding) {
        categoryAdapter = CategoryAdapter(this,this)

        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }

    }

    override fun onClick(title: String) {
        startNewActivity(title)
    }
}