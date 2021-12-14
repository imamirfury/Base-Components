package com.furybase.customviews.squareviews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


/** A LinearLayout that will always be square -- same width and height,
 * where the height is based off the width.  */

class FurySquareLinearLayout : LinearLayout {

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // Set a square layout.
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}