package com.furybase.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.furybase.extension.dpToPx


/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


class FuryItemDecorator(
    private val top: Int,
    private val bottom: Int,
    private val left: Int,
    private val right: Int
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = top.dpToPx()
        outRect.bottom = bottom.dpToPx()
        outRect.left = left.dpToPx()
        outRect.right = right.dpToPx()
    }
}