package com.furybase.listener

interface FuryItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}