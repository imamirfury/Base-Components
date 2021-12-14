package com.furybase.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import com.furybase.animations.BackOut
import com.furybase.animations.QuintOut


fun View.visibleListWithScale(delay: Long, duration: Long) {
    val alpha = ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f)
    alpha.startDelay = delay
    alpha.duration = duration
    alpha.start()

    val scaleY = ObjectAnimator.ofFloat(this, View.SCALE_Y, 4f, 1f)
    scaleY.startDelay = delay
    scaleY.duration = 700
    scaleY.interpolator = QuintOut()
    scaleY.start()
    scaleY.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            super.onAnimationStart(animation)
            visibility = View.VISIBLE
        }
    })
}

fun View.visibleWithAnimation(delay: Long, duration: Long) {
    val alpha = ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1f)
    alpha.startDelay = delay
    alpha.duration = duration
    alpha.start()
    val translate = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 500f, 0f)
    translate.duration = duration
    translate.interpolator = BackOut()
    translate.startDelay = delay
    translate.start()
    translate.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            super.onAnimationStart(animation)
            visibility = View.VISIBLE
        }
    })
}

fun View.goneWithAnimation(delay: Long, duration: Long, listener: AnimatorListenerAdapter?) {
    val alpha = ObjectAnimator.ofFloat(this, View.ALPHA, 1f, 0f)
    alpha.startDelay = delay
    alpha.duration = duration
    alpha.start()
    val translate = ObjectAnimator.ofFloat(this, View.TRANSLATION_Y, 0f, -200f)
    translate.duration = duration
    translate.interpolator = BackOut()
    translate.startDelay = delay
    translate.start()
    listener?.let {
        translate.addListener(it)
    }
}