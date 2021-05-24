package com.example.newmvi.ui

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Color.rgb
import android.graphics.ColorSpace
import android.util.Log
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.example.newmvi.R
import com.google.android.gms.common.util.Hex

private var lottieAnimationView: LottieAnimationView? = null

fun LottieAnimationView.mark(block: (String) -> Unit) {

    setOnClickListener {
        lottieAnimationView?.let {
            hideCheckBoxAnimation(it)
        }

        lottieAnimationView = this
        showCheckBoxAnimation(this)

        val color = when (id) {
            R.id.lottie_check_box_red    -> "#eb1a13"
            R.id.lottie_check_box_green  -> "#31d41c"
            R.id.lottie_check_box_blue   -> "#2a96d4"
            R.id.lottie_check_box_yellow -> "#e6ed18"
            R.id.lottie_check_box_purple -> "#c91cd9"
            else -> return@setOnClickListener
        }

        block.invoke(color)
    }
}

private fun showCheckBoxAnimation(lottie: LottieAnimationView) {
    lottie.apply {
        speed = 1f
        playAnimation()
    }
}

private fun hideCheckBoxAnimation(lottie: LottieAnimationView) {
    lottie.apply {
        speed = -1f
        playAnimation()
    }
}

fun LottieAnimationView.showLoadingAnimation() {
    visibility = View.VISIBLE
    repeatCount = ValueAnimator.INFINITE
    playAnimation()
}

fun LottieAnimationView.hideLoadingAnimation() {
    visibility = View.INVISIBLE
    cancelAnimation()
}