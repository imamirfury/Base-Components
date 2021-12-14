package com.furybase.base

import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.furybase.R

/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

abstract class FuryBaseDialog<B : ViewDataBinding>(context: Context) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), layoutRes, null, false)
        onDialogReady(savedInstanceState, binding as B)
        setContentView(binding.root)

        window?.let {
            val drawable =
                InsetDrawable(ContextCompat.getDrawable(context, R.drawable.transparent_bg), 0)
            it.setBackgroundDrawable(drawable)
            it.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
            it.setWindowAnimations(R.style.BeeBushWindowAnimation)
        }
    }

    @get:LayoutRes
    protected abstract val layoutRes: Int
    protected abstract fun onDialogReady(savedInstanceState: Bundle?, binding: B)

}