package com.example.newmvi.ui

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.newmvi.R
import com.example.newmvi.ui.custom.todoRecycleView.TodoRecycleView

@BindingAdapter("onItemClick")
fun TodoRecycleView.onItemClick(block: (TextView?) -> Unit) {
    this.setItemClick(block)
}

private var lottieAnimationView: LottieAnimationView? = null

@BindingAdapter("click")
fun LottieAnimationView.click(block: (Int?) -> Unit) {
    setOnClickListener {

        lottieAnimationView?.let {
            hideColorSelectionAnimation(it)
        }

        lottieAnimationView = this
        showColorSelectionAnimation(this)

        val color = when (id) {
            R.id.lottie_check_box_red -> Color.RED
            R.id.lottie_check_box_green -> Color.GREEN
            R.id.lottie_check_box_blue -> Color.BLUE
            R.id.lottie_check_box_yellow -> Color.YELLOW
            R.id.lottie_check_box_purple -> Color.MAGENTA
            else -> Color.TRANSPARENT
        }

        block(color)
    }
}


private fun showColorSelectionAnimation(lottie: LottieAnimationView) {
    lottie.apply {
        speed = 1f
        playAnimation()
    }
}

private fun hideColorSelectionAnimation(lottie: LottieAnimationView) {
    lottie.apply {
        speed = -1f
        playAnimation()
    }
}