package com.thetrusttech.getacarparts.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.thetrusttech.getacarparts.utils.Constants.Companion.DELAY_DURATION

/**
 * Created by SObaidullah on 8/9/2021.
 * Copyright (c) 2021 ANGUS SYSTEMS
 */
abstract class RecyclerAdapter<DataType, Binding> : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder<Binding>>() {

    private var item: MutableList<DataType>? = mutableListOf()

    abstract fun getLayoutResId(): Int

    abstract fun onBindData(
        listItem: DataType,
        dataBinding: Binding,
        position: Int
    )

    abstract fun onItemClick(listItem: DataType, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<Binding> {
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutResId(),
            parent,
            false
        )
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder<Binding>, position: Int) {
        item?.get(position)?.let { onBindData(it, holder.mDataBinding, position) }
        (holder.mDataBinding as ViewDataBinding).root.setOnClickListener {
            onItemClick(item?.get(position)!!, position)
        }
    }

    override fun getItemCount(): Int {
        return item?.size ?: 0
    }

    fun setItem(updatedItem: List<DataType>) {
        item?.clear()
        item?.addAll(updatedItem)
        this.notifyDataSetChanged()
    }

    class ItemViewHolder<BindingView>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var mDataBinding = binding as BindingView
    }

    fun updateItem(addItem: List<DataType>, diffCallBack: DiffUtil.Callback) {
        if(item == null) {
            throw IllegalArgumentException("Cannot set `null` item to the Recycler adapter");
        }
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallBack, true)

        item?.clear()
        item?.addAll(addItem)
        result.dispatchUpdatesTo(this)
    }

    @NonNull
    fun getCount(): Int {
        return item?.size ?: 0
    }

    fun getItems(): List<DataType>? {
        return item
    }

    fun removeItem(position: Int) {
        item?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeAnimation(view: View) {
        val anim: Animation = AnimationUtils.loadAnimation(
            view.context,
            android.R.anim.slide_out_right
        )
        anim.duration = DELAY_DURATION
        view.startAnimation(anim)
    }

    abstract fun setCurrentPage(pageName: String)

    fun getItemAtPosition(position: Int): DataType {
        return item!!.get(position)
    }

    enum class VIEW(val type: Int) {
        SURAH(0),
        JUZ(0)

    }
}