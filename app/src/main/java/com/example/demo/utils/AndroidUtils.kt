package com.example.demo.utils

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import android.widget.Space
import android.widget.TextView
import androidx.annotation.LayoutRes

fun getScreenWidth(windowManager: WindowManager): Int {
    val size = Point()
    windowManager.defaultDisplay.getSize(size)
    return size.x
}

fun getTextWidth(textView: TextView, text: String) = textView.paint.measureText(text).toInt()

fun setupTicker(texts: List<String>, @LayoutRes textViewRes: Int, textViewGroup: ViewGroup,
                titleTextView: TextView, title: String, windowManager: WindowManager) {
    val durationRate = 5L
    val layoutInflater = LayoutInflater.from(textViewGroup.context)
    val screenWidth = getScreenWidth(windowManager)
    var textViewGroupWidth = 0
    texts.forEachIndexed { index, text ->
        val textView = layoutInflater.inflate(textViewRes, textViewGroup, false) as TextView
        val textViewWidth = getTextWidth(textView, text) + textView.paddingLeft + textView.paddingRight
        textView.layoutParams.width = textViewWidth
        textView.text = text
        textViewGroup.addView(textView)
        textViewGroupWidth += textViewWidth

        if (index != texts.lastIndex) {
            val space = Space(textViewGroup.context)
            space.layoutParams = ViewGroup.LayoutParams(screenWidth, MATCH_PARENT)
            textViewGroup.addView(space)
            textViewGroupWidth += screenWidth
        }
    }
    textViewGroup.layoutParams.width = textViewGroupWidth
    titleTextView.text = title

    ObjectAnimator.ofFloat(textViewGroup, View.TRANSLATION_X, screenWidth.toFloat(), -textViewGroupWidth.toFloat()).apply {
        duration = (screenWidth + textViewGroupWidth) * durationRate
        interpolator = LinearInterpolator()
        repeatCount = ValueAnimator.INFINITE
        start()
    }

//    (AnimatorInflater.loadAnimator(textViewGroup.context, R.animator.animator_ticker) as ObjectAnimator).apply {
//        setProperty(TRANSLATION_X)
//        setFloatValues(screenWidth.toFloat(), -width.toFloat())
//        setValues(PropertyValuesHolder.ofFloat(TRANSLATION_X, screenWidth.toFloat(), -width.toFloat()))
//        duration = width * 5L
//        target = textViewGroup
//        start()
//    }
}
