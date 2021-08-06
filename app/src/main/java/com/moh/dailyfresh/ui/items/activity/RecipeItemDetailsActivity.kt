package com.moh.dailyfresh.ui.items.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.ActivityMainBinding
import com.moh.dailyfresh.models.response_maker.ApiErrorResponse
import com.moh.dailyfresh.models.response_maker.ApiSuccessResponse
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.items.viewmodel.ItemDetailsModel
import java.util.*

class RecipeItemDetailsActivity : BaseActivity() {
    private val viewModel: ItemDetailsModel by viewModels { getViewModelFactory!! }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_item_details)

        val id="807602"
        viewModel.getDetails(id).observe(this, androidx.lifecycle.Observer {

            when(it){
                is ApiSuccessResponse ->{
                    var response = it.body
                    println(response)
                }
             is ApiErrorResponse ->{
                 it.errorCode
             }
            }
        })
    }

}