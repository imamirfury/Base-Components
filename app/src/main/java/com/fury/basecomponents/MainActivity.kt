package com.fury.basecomponents

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.fury.basecomponents.databinding.ActivityMainBinding
import com.furybase.base.FuryBaseActivity
import com.furybase.extension.fetchAllCountriesFromAssets
import com.furybase.utils.FuryDatePicker

class MainActivity : FuryBaseActivity<ActivityMainBinding>() {

    val countries = fetchAllCountriesFromAssets()

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun getToolbar(binding: ActivityMainBinding): Toolbar? = null

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityMainBinding) {

    }
}