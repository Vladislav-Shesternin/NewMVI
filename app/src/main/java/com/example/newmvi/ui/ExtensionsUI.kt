package com.example.newmvi.ui

import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.airbnb.lottie.LottieAnimationView
import com.example.newmvi.R
import com.example.newmvi.ui.custom.todoRecycleView.TodoRecycleView

private var lottieAnimationView: LottieAnimationView? = null

fun LottieAnimationView.mark(block: (Int) -> Unit) {

    setOnClickListener {
        Log.i("TodoListFragment", "mark: ")
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