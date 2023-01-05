package com.thetrusttech.getacarparts.ui.items.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.databinding.ActivityRecipeItemDetailsBinding
import com.thetrusttech.getacarparts.models.response_maker.ApiErrorResponse
import com.thetrusttech.getacarparts.models.response_maker.ApiSuccessResponse
import com.thetrusttech.getacarparts.models.responses.details.Itemdetails
import com.thetrusttech.getacarparts.base.BaseActivity
import com.thetrusttech.getacarparts.ui.items.viewmodel.ItemDetailsModel

class RecipeItemDetailsActivity : BaseActivity() {
    private val viewModel: ItemDetailsModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityRecipeItemDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_item_details)

        val id = "484d98"
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

        var ingrediant = ""
        response.recipe.ingredients.forEach {
            ingrediant = ingrediant + it + "\n"
        }

        binding.tvIngredient.text = ingrediant

    }

}