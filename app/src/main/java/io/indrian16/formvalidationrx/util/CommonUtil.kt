package io.indrian16.formvalidationrx.util

import android.os.Build
import android.widget.Button
import androidx.annotation.RequiresApi
import io.indrian16.fromvalidation.R

object CommonUtil {

    const val GOOD = "Good"

    @RequiresApi(Build.VERSION_CODES.M)
    fun enableButton(button: Button): Button {

        val context = button.context
        val colorPrimary = context.getColor(R.color.colorPrimary)
        val colorWhite = context.getColor(R.color.colorWhite)

        button.let {

            it.setBackgroundColor(colorPrimary)
            it.setTextColor(colorWhite)
            it.isEnabled = true
            return it
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun disableButton(button: Button): Button {

        val context = button.context
        val colorPrimary = context.getColor(R.color.colorPrimary)
        val colorWhite = context.getColor(R.color.colorWhite)

        button.let {

            it.setBackgroundColor(colorWhite)
            it.setTextColor(colorPrimary)
            it.isEnabled = false
            return it
        }
    }
}