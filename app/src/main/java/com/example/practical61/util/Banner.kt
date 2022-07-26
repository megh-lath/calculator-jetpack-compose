package com.example.practical61.util

import android.app.Activity
import android.content.Context
import com.example.practical61.R
import com.tapadoo.alerter.Alerter

fun showBanner(context: Context, message: String) {
    Alerter.create(context as Activity)
        .setText(message)
        .setIcon(R.drawable.ic_baseline_block_24)
        .disableOutsideTouch()
        .enableSwipeToDismiss()
        .setBackgroundColorRes(R.color.teal_700)
        .setDuration(3500)
        .show()
}
