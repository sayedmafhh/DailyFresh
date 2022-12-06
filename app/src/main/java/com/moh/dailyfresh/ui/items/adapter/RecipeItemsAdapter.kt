package com.moh.dailyfresh.ui.items.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.LayoutRecipeItemBinding
import com.moh.dailyfresh.models.responses.items.Items
import com.moh.dailyfresh.ui.base.RecyclerAdapter
import com.moh.dailyfresh.ui.items.IItem

class RecipeItemsAdapter(private val context: Context, private val listener: IItem): RecyclerAdapter<Items.Recipe, LayoutRecipeItemBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.layout_recipe_item
    }

    override fun onBindData(
        listItem: Items.Recipe,
        dataBinding: LayoutRecipeItemBinding,
        position: Int
    ) {
        dataBinding.tvTitle.text = listItem.title
        dataBinding.tvPublisher.text = listItem.publisher
        Glide.with(context)
            .load(listItem.imageUrl)
            .centerCrop()
            .into(dataBinding.ivItem)
    }

    override fun onItemClick(listItem: Items.Recipe, position: Int) {
        listener.onClick(listItem.id!!)
    }

    override fun setCurrentPage(pageName: String) {
        TODO("Not yet implemented")
    }
}