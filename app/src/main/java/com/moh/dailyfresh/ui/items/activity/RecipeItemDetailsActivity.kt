package com.moh.dailyfresh.ui.items.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.databinding.ActivityRecipeItemDetailsBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.models.responses.details.Itemdetails
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.items.viewmodel.ItemDetailsModel
import com.moh.dailyfresh.utils.Constants.Companion.EXTRA_ID

class RecipeItemDetailsActivity : BaseActivity() {
    private val viewModel: ItemDetailsModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityRecipeItemDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_item_details)

        val id = intent.getStringExtra(EXTRA_ID) ?: ""
        viewModel.getDetails(id).observe(this, androidx.lifecycle.Observer {

            when (it) {
                is ApiSuccessResponse -> {
                    var response = it.body
                    updateUI(response)
                }
                is ApiErrorResponse -> {
                    it.errorCode
                }
            }
        })
    }

    private fun updateUI(response: Itemdetails) {
        Glide
            .with(this)
            .load(response.recipe.imageUrl)
            .centerCrop()
            .into(binding.ivItem)
        binding.tvPublisher.text = "Publisher : " + response.recipe.publisher
        binding.tvTitle.text = response.recipe.title

        var ingrediant = ""
        response.recipe.ingredients.forEach {
            ingrediant = ingrediant + it + "\n"
        }

        binding.tvIngredient.text = ingrediant


    }

}