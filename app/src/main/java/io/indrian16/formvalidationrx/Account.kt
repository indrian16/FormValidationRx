package io.indrian16.formvalidationrx

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Account(

        var username: String? = null,
        var email: String? = null,
        var password: String? = null

): Parcelable