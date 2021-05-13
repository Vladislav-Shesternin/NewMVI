package com.example.newmvi.ui

import android.animation.ValueAnimator
import android.graphics.Color
import android.util.Log
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import com.example.newmvi.R

// ------------------------------------------------------------
//                  Lottie CheckBox
// ------------------------------------------------------------

private var lottieAnimationView: LottieAnimationView? = null

fun LottieAnimationView.mark(block: (Int) -> Unit) {

    setOnClickListener {
        lottieAnimationView?.let {
            hideCheckBoxAnimation(it)
        }

        lottieAnimationView = this
        showCheckBoxAnimation(this)

        val color = when (id) {
            R.id.lottie_check_box_red -> Color.RED
            R.id.lottie_check_box_green -> Color.GREEN
            R.id.lottie_check_box_blue -> Color.BLUE
            R.id.lottie_check_box_yellow -> Color.YELLOW
            R.id.lottie_check_box_purple -> Color.MAGENTA
            else -> Color.TRANSPARENT
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

// ------------------------------------------------------------
//                  Lottie ProgressBar
// ------------------------------------------------------------

fun LottieAnimationView.showLoadingAnimation() {
    visibility = View.VISIBLE
    repeatCount = ValueAnimator.INFINITE
    playAnimation()
}

fun LottieAnimationView.hideLoadingAnimation() {
    visibility = View.INVISIBLE
    cancelAnimation()
}