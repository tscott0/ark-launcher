package com.scott.tom.arklauncher.ark

import android.graphics.drawable.Drawable

data class AppDetails(
        val name: String,
        val iconDrawable: Drawable,
        val onClickAction: () -> Unit,
        val onLongClickAction: () -> Boolean
)
