package com.thetrusttech.getacarparts.ui.items.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ActivityMainBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.ui.base.BaseActivity
import com.thetrusttech.getacarparts.ui.items.viewmodel.ItemsViewModel
import com.thetrusttech.getacarparts.utils.Constants.Companion.EXTRA_TITLE

class RecipeItemsActivity : BaseActivity() {
    private val viewModel: ItemsViewModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_item)

        //if intent.getStringExtra(EXTRA_TITLE) is null then set empty string. -- force  !!
        val query = intent.getStringExtra(EXTRA_TITLE) ?: ""
        val page = "1"

        viewModel.getRecipes(query, page).observe(this, Observer {
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