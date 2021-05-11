package com.sun.moviedb_54.ultis

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    override fun onTouchEvent(ev: MotionEvent?) = false

    override fun onInterceptTouchEvent(event: MotionEvent?) = false
}
