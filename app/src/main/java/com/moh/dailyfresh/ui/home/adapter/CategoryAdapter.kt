package com.moh.dailyfresh.ui.home.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.moh.dailyfresh.R
import com.moh.dailyfresh.databinding.LayoutCategoryBinding
import com.moh.dailyfresh.models.responses.category.Category
import com.moh.dailyfresh.models.responses.category.Recipe
import com.moh.dailyfresh.ui.base.RecyclerAdapter
import com.moh.dailyfresh.ui.home.ICategory

class CategoryAdapter(private val context: Context, private val listener: ICategory)
    : RecyclerAdapter<Category, LayoutCategoryBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.layout_category
    }

    override fun onBindData(listItem: Category, dataBinding: LayoutCategoryBinding, position: Int) {
        dataBinding.tvTitle.text = listItem.title
        Glide.with(context)
            .load(listItem.imageUrl)
            .centerCrop()
            .into(dataBinding.ivItem)
    }

    override fun onItemClick(listItem: Category, position: Int) {
        listener.onClick(listItem.title!!)
    }

    override fun setCurrentPage(pageName: String) {
        // not setting anything for now
    }

}