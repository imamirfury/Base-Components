package com.furybase.utils


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.furybase.R

/***
 * Created By Amir Fury on December 14 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


class FuryFilterFlowLayout(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    private var mHorizontalSpacing: Int = 20
    private var mVerticalSpacing: Int = 0
    private val mPaint: Paint

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout)
        try {
            mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_fHorizontalSpacing, 0)
            mVerticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_fVerticalSpacing, 0)
        } finally {
            a.recycle()
        }

        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.color = 0xffff
        mPaint.strokeWidth = 2.0f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthLimit = View.MeasureSpec.getSize(widthMeasureSpec) - paddingRight
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)

        val growHeight = widthMode != View.MeasureSpec.UNSPECIFIED

        var width = 0

        var currentWidth = paddingLeft
        var currentHeight = paddingTop

        var maxChildHeight = 0

        var breakLine = false
        var newLine = false
        var spacing = 0

        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)

            val lp = child.layoutParams as LayoutParams
            spacing = mHorizontalSpacing

            if (lp.horizontalSpacing >= 0) {
                spacing = lp.horizontalSpacing
            }

            if (growHeight && (breakLine || currentWidth + child.measuredWidth > widthLimit)) {
                newLine = true
                currentHeight += maxChildHeight + mVerticalSpacing + 20

                width = Math.max(width, currentWidth - spacing)

                currentWidth = paddingLeft
                maxChildHeight = 0
            } else {
                newLine = false
            }

            maxChildHeight = Math.max(maxChildHeight, child.measuredHeight)

            lp.x = currentWidth
            lp.y = currentHeight

            currentWidth += child.measuredWidth + spacing

            breakLine = lp.breakLine
        }

        if (!newLine) {
            width = Math.max(width, currentWidth - spacing)
        }

        width += paddingRight
        val height = currentHeight + maxChildHeight + paddingBottom

        setMeasuredDimension(View.resolveSize(width, widthMeasureSpec), View.resolveSize(height, heightMeasureSpec))
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            child.layout(lp.x, lp.y, lp.x + child.measuredWidth, lp.y + child.measuredHeight)
        }
    }

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        val more = super.drawChild(canvas, child, drawingTime)
        val lp = child.layoutParams as LayoutParams
        if (lp.horizontalSpacing > 0) {
            val x = child.right.toFloat()
            val y = child.top + child.height / 2.0f
            canvas.drawLine(x, y - 4.0f, x, y + 4.0f, mPaint)
            canvas.drawLine(x, y, x + lp.horizontalSpacing, y, mPaint)
            canvas.drawLine(x + lp.horizontalSpacing, y - 4.0f, x + lp.horizontalSpacing, y + 4.0f, mPaint)
        }
        if (lp.breakLine) {
            val x = child.right.toFloat()
            val y = child.top + child.height / 2.0f
            canvas.drawLine(x, y, x, y + 6.0f, mPaint)
            canvas.drawLine(x, y + 6.0f, x + 6.0f, y + 6.0f, mPaint)
        }
        return more
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams {

        return LayoutParams(p.width, p.height)
    }

    class LayoutParams : ViewGroup.LayoutParams {
        var horizontalSpacing = 25
        var breakLine: Boolean = false
        internal var x: Int = 0
        internal var y: Int = 0

        constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout_LayoutParams)
            try {
                horizontalSpacing =
                    a.getDimensionPixelSize(R.styleable.FlowLayout_LayoutParams_layout_horizontalSpacing, -1)
                breakLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_layout_breakLine, false)
            } finally {
                a.recycle()
            }
        }

        constructor(w: Int, h: Int) : super(w, h)
    }
}

