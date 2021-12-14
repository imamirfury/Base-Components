package com.furybase.listener


/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


interface FuryItemClickListener<T> {
    fun onItemClick(item: T, position: Int)
}