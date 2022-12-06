package com.moh.dailyfresh.ui.items.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.databinding.ActivityRecipeItemBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.items.IItem
import com.moh.dailyfresh.ui.items.adapter.RecipeItemsAdapter
import com.moh.dailyfresh.ui.items.viewmodel.ItemsViewModel
import com.moh.dailyfresh.utils.Constants.Companion.EXTRA_ID
import com.moh.dailyfresh.utils.Constants.Companion.EXTRA_TITLE

class RecipeItemsActivity : BaseActivity(), IItem {
    private val viewModel: ItemsViewModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityRecipeItemBinding
    private lateinit var itemAdapter: RecipeItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_item)

        //if intent.getStringExtra(EXTRA_TITLE) is null then set empty string. -- force  !!
        val query = intent.getStringExtra(EXTRA_TITLE) ?: ""
        val page = "1"

        viewModel.getRecipes(query, page).observe(this, Observer {
            when(it) {
                is ApiSuccessResponse -> {
                    val response = it.body
                   itemAdapter.setItem(response.recipes!!)
                }
                is ApiErrorResponse -> {
                    Log.d("RecipeItemsApiSuccess", it.errorMessage)
                }
            }
        })

        initList()
    }

    private fun initList() {
        itemAdapter = RecipeItemsAdapter(this,this)
        binding.rvItem.apply {
            layoutManager = LinearLayoutManager(this@RecipeItemsActivity, LinearLayoutManager.VERTICAL, false)
            adapter = itemAdapter
        }

    }

    private fun startNewActivity(id: String) {
        val intent = Intent(this, RecipeItemDetailsActivity::class.java).apply {
            putExtra(EXTRA_ID, id)
        }
        startActivity(intent)
    }

    override fun onClick(id: String) {
        startNewActivity(id)
    }
}