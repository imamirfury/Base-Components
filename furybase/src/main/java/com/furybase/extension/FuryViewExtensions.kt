package com.furybase.extension

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.furybase.R
import com.furybase.databinding.LayoutCustomProgressBinding
import com.google.android.material.tabs.TabLayout


/***
 * Created By Amir Fury on December 9 2021
 *
 * Email: Fury.amir93@gmail.com
 * */

private var dialog: Dialog? = null
fun Context.showProgress(message: String?, isCancellable: Boolean) {
    val dialogBinding =
        inflateBinding(R.layout.layout_custom_progress) as LayoutCustomProgressBinding
    val builder = AlertDialog.Builder(this)
    builder.setCancelable(isCancellable)
    builder.setView(dialogBinding.root)
    dialogBinding.processingText.text = message
    dialog = builder.create()
    dialog?.show()
}

fun Context.hideProgress() {
    dialog?.let {
        if (it.isShowing) {
            it.dismiss()
        }
    }
}

fun EditText.setErrorOnEditText(errorMessage: String) {
    error = errorMessage
}

fun TextView.string(): String = text.toString().trim()
fun EditText.string(): String = text.toString().trim()

fun Context.toast(message: String?) {
    Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.reHeight(height: Int) {
    val newHeight = if (height >= 0) height else 0

    if (newHeight != layoutParams.height) {
        layoutParams.height = height
        layoutParams = layoutParams
    }
}

fun View.reWidth(width: Int) {
    val newWidth = if (width >= 0) width else 0

    if (newWidth != layoutParams.width) {
        layoutParams.width = width
        layoutParams = layoutParams
    }
}

fun View.resize(width: Int, height: Int) {
    var newWidth = layoutParams.width
    var newHeight = layoutParams.height

    if (width >= 0) newWidth = width
    if (height >= 0) newHeight = height

    if (newWidth != layoutParams.width || newHeight != layoutParams.height) {
        layoutParams.width = width
        layoutParams.height = height
        layoutParams = layoutParams
    }
}

@BindingAdapter("setTextOrHideView")
fun TextView.setTextOrHideView(text: String?) {
    if (text.isNullOrBlank()) {
        hide()
        return
    }
    show()
    setText(text)
}

fun TextView.setTextOnView(text: String?) {
    show()
    this.text = text.toString()
}

fun TextView.setColorOnText(colorRes: Int) {
    setTextColor(ContextCompat.getColor(context, colorRes))
}

fun TabLayout.setTabMargins(left: Int, top: Int, right: Int, bottom: Int) {
    try {
        for (i in 0 until tabCount) {
            val tab = (getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            tab.requestLayout()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun EditText.hideSoftInput() {
    val inputManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun EditText.showSoftInput() {
    requestFocus()
    val inputMethodManager: InputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.toggleSoftInputFromWindow(
        this.windowToken,
        InputMethodManager.SHOW_FORCED,
        0
    )
}


fun String.makeWordColored(startFrom: Int, endTo: Int, color: Int): SpannableString {
    val spannableString = SpannableString(this)
    val foregroundColorSpan = ForegroundColorSpan(color)
    spannableString.setSpan(foregroundColorSpan, startFrom, endTo, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    return spannableString
}

fun TextView.setAndMakeWorkBold(dataString: String?, startFrom: Int, endTo: Int) {
    if (dataString.isNullOrBlank()) {
        return
    }
    val spannableStringBuilder = SpannableStringBuilder(dataString)
    spannableStringBuilder.setSpan(
        StyleSpan(Typeface.BOLD),
        startFrom,
        endTo,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    text = spannableStringBuilder
}

fun EditText.setNotEditable() {
    isEnabled = false
    keyListener = null
}

fun Context.inflateBinding(layoutId: Int): ViewDataBinding = DataBindingUtil.inflate(
    LayoutInflater.from(this), layoutId, null, false
)

fun ImageView.setDrawableOnImage(drawableId: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, drawableId))
}

fun View.setViewEnable(enable: Boolean) {
    isEnabled = enable
}

fun Context.showPopMenu(
    anchorView: View,
    menuId: Int,
    listener: PopupMenu.OnMenuItemClickListener
) {
    val popUp = PopupMenu(this, anchorView)
    popUp.setOnMenuItemClickListener(listener)
    val inflater = popUp.menuInflater
    inflater.inflate(menuId, popUp.menu)
    popUp.show()
}


fun AppCompatActivity.hideSoftInput() {
    val inputManager =
        this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = currentFocus
    if (view == null) {
        view = View(this)
    }
    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
}