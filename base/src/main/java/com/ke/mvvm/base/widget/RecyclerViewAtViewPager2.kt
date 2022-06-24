package com.ke.mvvm.base.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2


class RecyclerViewAtViewPager2 : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var startX = 0f
    private var startY = 0f

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = ev.x
                startY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {

                val endX = ev.x
                val endY = ev.y
                val disX = Math.abs(endX - startX)
                val disY = Math.abs(endY - startY)

                startX = endX
                startY = endY

                if (disY > disX) {
                    //发生了竖向滑动
                    getViewPager2(parent)?.isUserInputEnabled = false
                }

            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                getViewPager2(parent)?.isUserInputEnabled = true

            }
        }
        return super.dispatchTouchEvent(ev)
    }


    private fun getViewPager2(viewParent: ViewParent): ViewPager2? {

        return when {
            viewParent is ViewPager2 -> {
                viewParent
            }
            viewParent.parent != null -> {
                getViewPager2(viewParent.parent)
            }
            else -> {
                null
            }
        }
    }
}
