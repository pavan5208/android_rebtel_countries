package com.rebtel.countries.utils

import android.view.View

fun View.remove() {
    if (visibility != View.GONE) visibility = View.GONE
}

fun View.show() {
    if (visibility != View.VISIBLE) visibility = View.VISIBLE
}


