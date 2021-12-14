package com.furybase.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.furybase.BR
import com.furybase.listener.FuryItemClickListener

/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

abstract class FuryBaseListAdapter<T>(diffCallBack: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, FuryBaseListAdapter<T>.FuryBaseViewHolder>(diffCallBack) {

    private var listener: FuryItemClickListener<T>? = null

    fun setItemClick(clickListener: FuryItemClickListener<T>) {
        this.listener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuryBaseViewHolder {
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), viewType, parent, false)
        return FuryBaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FuryBaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FuryBaseViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.setVariable(BR.model, item)
            binding.executePendingBindings()
            itemView.setOnClickListener { listener?.onItemClick(item, adapterPosition) }
        }
    }
}