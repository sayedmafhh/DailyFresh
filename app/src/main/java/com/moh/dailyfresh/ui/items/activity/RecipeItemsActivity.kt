package com.moh.dailyfresh.ui.items.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.items.viewmodel.ItemsViewModel
import com.moh.dailyfresh.ui.viewmodels.MainViewModel
import com.moh.dailyfresh.utils.Constants.Companion.EXTRA_TITLE

class RecipeItemsActivity : BaseActivity() {
    private val viewModel: ItemsViewModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_item)

        val query: String? = intent.getStringExtra(EXTRA_TITLE)
        val page: String = "1"

        viewModel.getRecipes(query!!, page).observe(this, Observer {
            when(it) {
                is ApiSuccessResponse -> {
                    Log.d("RecipeItemsApiSuccess", it.body.toString())
                }
                is ApiErrorResponse -> {
                    Log.d("RecipeItemsApiSuccess", it.errorMessage)
                }
            }
        })
    }
}