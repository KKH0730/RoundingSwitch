package com.seno.arswitch.extensions

import android.content.res.Resources
import android.util.TypedValue

fun Float.dpToPx(resources: Resources): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        resources.displayMetrics
    ).toInt()