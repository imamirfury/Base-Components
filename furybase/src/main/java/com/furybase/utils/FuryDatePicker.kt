package com.furybase.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*


/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

class FuryDatePicker(private val datePickerListener: DatePickerListener?) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireActivity(), this, year, month, day)
        c.set(Calendar.YEAR, year - 15)
        datePickerDialog.datePicker.maxDate = c.timeInMillis
        return datePickerDialog
    }


    override fun onDateSet(
        view: android.widget.DatePicker?,
        year: Int,
        month: Int,
        dayOfMonth: Int
    ) {
        val newMonth = month + 1
        datePickerListener?.onDateSelected(dayOfMonth, newMonth, year)
    }

    interface DatePickerListener {
        fun onDateSelected(dayOfMonth: Int, month: Int, year: Int)
    }
}