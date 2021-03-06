package com.seljabali.appcompattheming.ui.landingpage

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.seljabali.appcompattheming.R


enum class LandingPageItems(@StringRes override val titleStringId: Int,
                            @DrawableRes override val iconId: Int = R.drawable.ic_launcher_background) : LandingItem {
    WIDGETS(R.string.widgets, R.drawable.ic_widgets),
    DESIGN(R.string.design, R.drawable.ic_paint)
}

