package com.furybase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/***
 * Created By Amir Fury on December 9 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

abstract class FuryBaseFragment<B : ViewBinding>(private val bindingInflater: (inflater: LayoutInflater) -> B) :
    Fragment() {

    private var _binding: B? = null

    val binding: B
        get() = _binding as B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw IllegalStateException("Binding can not be null")
        onFragmentReady(binding)
        return binding.root
    }
    protected abstract fun onFragmentReady(binding : B)
}