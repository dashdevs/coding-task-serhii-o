package com.example.themoviedatabase.utils.extentions

import android.view.View
import androidx.constraintlayout.widget.Group

const val MIN_CLICK_INTERVAL: Long = 1000

fun View.onClick(code: (View?) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var mLastClickTime: Long = 0

        fun onSingleClick(v: View?) {
            code.invoke(v)
        }

        override fun onClick(v: View?) {
            val currentClickTime = System.currentTimeMillis()
            if ((currentClickTime - mLastClickTime) >= MIN_CLICK_INTERVAL) {
                mLastClickTime = currentClickTime
                onSingleClick(v)
            } else {
                return
            }
        }
    })
}

fun View.onDoubleClick(code: (View?) -> Unit) {
    var mLastClickTime: Long = 0
    setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            val currentClickTime = System.currentTimeMillis()
            if (currentClickTime - mLastClickTime < MIN_CLICK_INTERVAL) {
                onDoubleClick(v)
            }
            mLastClickTime = currentClickTime
        }
        fun onDoubleClick(v: View?) {
            code.invoke(v)
        }
    })
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isNotVisible(): Boolean = visibility == View.GONE || visibility == View.INVISIBLE

fun View.gone(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.GONE
}

fun View.invisible(condition: Boolean) {
    visibility = if (condition) View.VISIBLE else View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Group.setAlphaForAll(alpha: Float) = referencedIds.forEach {
    rootView.findViewById<View>(it).alpha = alpha
}